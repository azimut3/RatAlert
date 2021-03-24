package com.iei.ratallert.view;

import com.iei.ratallert.database.controllers.MidHourStatService;
import com.iei.ratallert.database.entities.Stat;
import com.iei.ratallert.model.LifeQualitySensorDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class StatsViewer {

    @Autowired
    MidHourStatService midHourStatService;

    @GetMapping("/v1/stats")
    public String greeting(Model model) {
        String hourlyStats = midHourStatService.getAll().toString();
        model.addAttribute("hourlyStats", hourlyStats);
        return "statsPage";
    }

    @GetMapping(path = "/v1/currentCondition")
    public String getData(Model model){
        RestTemplate restTemplate = new RestTemplate();

        LifeQualitySensorDataModel lifeQualityData = restTemplate
                .getForObject("http://94.158.155.196:82" + "/lifeQuality", LifeQualitySensorDataModel.class);

        Stat curStat = new Stat(lifeQualityData);

        model.addAttribute("hourlyStats", curStat);

        return "Success";
    }
}
