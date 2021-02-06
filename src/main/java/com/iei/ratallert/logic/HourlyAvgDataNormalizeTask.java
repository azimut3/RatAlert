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
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

@Log4j2
@Service
public class HourlyAvgDataNormalizeTask extends TimerTask {

    @Autowired
    HourlyStatService hourlyStatService;
    @Autowired
    StatsService statsService;

    @Override
    public void run() {
        LocalDateTime curDate = LocalDateTime.now();
        log.info("Updating stats data. Current time: " + curDate);
        //List<Stat> lastHourStatsList = hourlyStatService.getLastHourData(curDate);
        log.info("lastHourStatsList");
        //log.info(lastHourStatsList);
        List<Stat> statList = statsService.getAllStatsSorted();

        LocalDateTime datetime = null;
        List<Stat> tempStatsList = new ArrayList<>();
        List<HourlyStat> lastHourStatsList = new ArrayList<>();
        for(var stat : statList){
            if(datetime == null) datetime = stat.getCreationDate().minusMinutes(stat.getCreationDate().getMinute());
            if(stat.getCreationDate().isBefore(datetime.plusHours(1).plusMinutes(1))){
                tempStatsList.add(stat);
            } else {
                HourlyStat hourlyStat = normalizeHourlyStats(tempStatsList);
                hourlyStat.setCreationDate(datetime.plusHours(1));
                datetime = null;
                lastHourStatsList.add(hourlyStat);
            }
        }

        hourlyStatService.saveAll(lastHourStatsList);
        //statsService.deleteAll(statList);
    }

    public HourlyStat normalizeHourlyStats(List<Stat> lastHourStatsList){
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
