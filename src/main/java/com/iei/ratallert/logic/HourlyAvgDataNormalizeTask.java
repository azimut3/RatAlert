package com.iei.ratallert.logic;

import com.iei.ratallert.database.controllers.StatsService;
import com.iei.ratallert.database.entities.HourlyStat;
import com.iei.ratallert.database.entities.Stat;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

@Log4j2
@Service
public class HourlyAvgDataNormalizeTask extends TimerTask {

    @Autowired
    StatsService statsService;

    @Override
    public void run() {
        Date curDate = new Date();
        log.info("Updating stats data. Current time: " + curDate);
        List<Stat> lastHourStatsList = statsService.getLastHourData(curDate);
        log.info("lastHourStatsList");
        log.info(lastHourStatsList);

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

        statsService.save(avgStat);
    }
}
