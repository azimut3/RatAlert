package com.iei.ratallert.services.googleService;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import com.google.api.services.actions_fulfillment.v2.model.User;
import com.iei.ratallert.services.lifeQuality.database.entities.Stat;
import com.iei.ratallert.services.lifeQuality.model.LifeQualitySensorDataModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ResourceBundle;

@Log4j2
@Service
public class RatAlertApp extends DialogflowApp {
    @ForIntent("home_conditions")
    public ActionResponse homeConditions(ActionRequest request) {
        log.info("home_conditions intent start.");
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        String homeConditionPattern = "Current room temperature is %s. The humidity is %s. The air quality is %s that means %s";

        RestTemplate restTemplate = new RestTemplate();
        LifeQualitySensorDataModel lifeQualityData = restTemplate.getForObject("http://94.158.155.196:82" + "/lifeQuality",
                LifeQualitySensorDataModel.class);
        Stat curStat = new Stat(lifeQualityData);

        String homeCondition = String.format(homeConditionPattern,
                curStat.getRoomTemperature(),
                curStat.getRoomHumidity(),
                curStat.getRoomAirQualityPpmValue(),
                curStat.getRoomAirQualityLevel());

        responseBuilder.add(homeCondition);

        log.info("Welcome intent end.");
        return responseBuilder.build();
    }

    @ForIntent("Default Welcome Intent")
    public ActionResponse welcome(ActionRequest request) {
        log.info("Welcome intent start.");
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        responseBuilder.add("Something worked!");

        log.info("Welcome intent end.");
        return responseBuilder.build();
    }
}
