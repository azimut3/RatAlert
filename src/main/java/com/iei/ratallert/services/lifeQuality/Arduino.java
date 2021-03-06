package com.iei.ratallert.services.lifeQuality;

import com.iei.ratallert.services.lifeQuality.database.controllers.HourlyStatService;
import com.iei.ratallert.services.lifeQuality.database.controllers.MidHourStatService;
import com.iei.ratallert.services.lifeQuality.database.entities.HourlyStat;
import com.iei.ratallert.services.lifeQuality.database.entities.Stat;
import com.iei.ratallert.services.lifeQuality.schedulers.HourlyAvgDataNormalizeTask;
import com.iei.ratallert.services.lifeQuality.model.LifeQualitySensorDataModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${ESP8266_SMART_HOME}")
    String lifeQualityEndpoint;

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
                .getForObject(lifeQualityEndpoint + "/lifeQuality", LifeQualitySensorDataModel.class);
        Stat curStat = new Stat(lifeQualityData);
        return curStat;
    }

    @GetMapping("/api/v1/hourlyStatsData")
    public List<HourlyStat> greeting(Model model) {
        List<HourlyStat> hourlyStats = hourlyStatService.findAllAndOrderByCreationDate();
        return hourlyStats;
    }
}
