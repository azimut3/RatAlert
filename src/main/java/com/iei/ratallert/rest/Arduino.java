package com.iei.ratallert.rest;

import com.iei.ratallert.database.controllers.HourlyStatService;
import com.iei.ratallert.database.controllers.MidHourStatService;
import com.iei.ratallert.database.entities.HourlyStat;
import com.iei.ratallert.database.entities.Stat;
import com.iei.ratallert.schedulers.HourlyAvgDataNormalizeTask;
import com.iei.ratallert.model.LifeQualitySensorDataModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
@RestController
public class Arduino {
    @Autowired
    MidHourStatService midHourStatService;
    @Autowired
    HourlyStatService hourlyStatService;
    @Autowired
    HourlyAvgDataNormalizeTask hourlyAvgDataNormalizeTask;

    @GetMapping(path = "/api/v1/esp")
    public String test(){
        return "Success";
    }


    @GetMapping(path = "/api/v1/recalculateHourlyStats")
    public String recalculateHourlyStats(){
        hourlyAvgDataNormalizeTask.run();
        return "Success";
    }

    @GetMapping(path = "/api/v1/currentCondition")
    public Stat getCurrentConditions(){
        RestTemplate restTemplate = new RestTemplate();

        LifeQualitySensorDataModel lifeQualityData = restTemplate
                .getForObject("http://94.158.155.196:82" + "/lifeQuality", LifeQualitySensorDataModel.class);
        Stat curStat = new Stat(lifeQualityData);
        return curStat;
    }

    @GetMapping("/api/v1/hourlyStatsData")
    public List<HourlyStat> greeting(Model model) {
        List<HourlyStat> hourlyStats = hourlyStatService.findAllAndOrderByCreationDate();
        return hourlyStats;
    }
}
