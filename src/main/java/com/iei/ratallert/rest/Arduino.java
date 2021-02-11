package com.iei.ratallert.rest;

import com.iei.ratallert.database.controllers.StatsService;
import com.iei.ratallert.database.entities.HourlyStat;
import com.iei.ratallert.database.entities.Stat;
import com.iei.ratallert.logic.HourlyAvgDataNormalizeTask;
import com.iei.ratallert.logic.LifeQualityData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RestController
public class Arduino {
    @Autowired
    StatsService statsService;
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
    public LifeQualityData getCurrentConditions(){
        RestTemplate restTemplate = new RestTemplate();

        LifeQualityData lifeQualityData = restTemplate
                .getForObject("http://94.158.155.196:82" + "/lifeQuality", LifeQualityData.class);
        return lifeQualityData;
    }


}
