package com.iei.ratallert.logic;

import com.iei.ratallert.database.controllers.HourlyStatService;
import com.iei.ratallert.database.controllers.StatsService;
import com.iei.ratallert.database.entities.HourlyStat;
import com.iei.ratallert.database.entities.Stat;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Log4j2
@Service
public class HourlyAvgDataNormalizeTask extends TimerTask {

    @Autowired
    HourlyStatService hourlyStatService;
    @Autowired
    StatsService statsService;

    @Override
    public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00");
        LocalDateTime curDate = LocalDateTime.now();
        log.info("Updating stats data. Current time: " + curDate);
        //List<Stat> lastHourStatsList = hourlyStatService.getLastHourData(curDate);
        log.info("lastHourStatsList");
        //log.info(lastHourStatsList);
        List<Stat> statList = statsService.getAllStatsSorted();

        Map<String, List<Stat>> dataMap = new HashMap<>();
        List<HourlyStat> lastHourStatsList = new ArrayList<>();

        for(var stat : statList){
            String timeRepresentation = stat.getCreationDate().format(formatter);
            if(dataMap.containsKey(timeRepresentation)){
                log.info("Contains key");
                log.info(stat);
                log.info(dataMap.get(timeRepresentation));
                dataMap.get(timeRepresentation).add(stat);
            } else {
                var mapStatList = new ArrayList<Stat>();
                mapStatList.add(stat);
                dataMap.put(timeRepresentation, mapStatList);
            }
        }
        for (String str : dataMap.keySet()) {
            HourlyStat hourlyStat = normalizeHourlyStats(dataMap.get(str));
            hourlyStat.setCreationDate(LocalDateTime.parse(str, formatter));
            lastHourStatsList.add(hourlyStat);
        }

        hourlyStatService.saveAll(lastHourStatsList);
        statsService.deleteAll(statList);
    }

    public HourlyStat normalizeHourlyStats(List<Stat> lastHourStatsList){
        log.info("Normalizing list: " + lastHourStatsList);
        DescriptiveStatistics temperatureStatistics = new DescriptiveStatistics();
        DescriptiveStatistics humidityStatistics = new DescriptiveStatistics();
        DescriptiveStatistics airQualityStatistics = new DescriptiveStatistics();

        lastHourStatsList.stream().forEach((data) -> {
            temperatureStatistics.addValue(data.getRoomTemperature());
            humidityStatistics.addValue(data.getRoomHumidity());
            airQualityStatistics.addValue(data.getRoomAirQualityPpmValue());
        });

        double temperatureMedian = temperatureStatistics.getPercentile(50);
        double humidityMedian = humidityStatistics.getPercentile(50);
        double airQualityMedian = airQualityStatistics.getPercentile(50);

        HourlyStat avgStat = new HourlyStat();
        avgStat.setRoomHumidity(humidityMedian)
                .setRoomTemperature(temperatureMedian)
                .setRoomAirQualityPpmValue(airQualityMedian);

        return avgStat;
    }
}
