package com.iei.ratallert.services.lifeQuality.rest;

import com.iei.ratallert.services.lifeQuality.database.controllers.HourlyStatService;
import com.iei.ratallert.services.lifeQuality.database.entities.HourlyStat;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/chart-data/v1")
public class ChartDataEndpoint {
    @Autowired
    HourlyStatService hourlyStatService;

    @GetMapping("/past24HourData")
    public List<HourlyStat> getPast24HourData(Model model) {
        List<HourlyStat> hourlyStats = hourlyStatService.findAllBetweenDates(LocalDateTime.now().minusDays(1), LocalDateTime.now());
        return hourlyStats;
    }

    @GetMapping("/past3DaysData")
    public List<HourlyStat> getPast3DaysData(Model model) {
        List<HourlyStat> hourlyStats = hourlyStatService.findAllBetweenDates(LocalDateTime.now().minusDays(3), LocalDateTime.now());
        return hourlyStats;
    }

    @GetMapping("/pastWeekData")
    public List<HourlyStat> getPastWeekData(Model model) {
        List<HourlyStat> hourlyStats = hourlyStatService.findAllBetweenDates(LocalDateTime.now().minusDays(7), LocalDateTime.now());
        return hourlyStats;
    }
}
