package com.iei.ratallert.services.googleService;

import com.google.actions.api.*;
import com.google.actions.api.response.ResponseBuilder;
import com.google.api.services.actions_fulfillment.v2.model.User;
import com.iei.ratallert.services.googleService.api.generic.GAFirstSimple;
import com.iei.ratallert.services.googleService.api.generic.GAPrompt;
import com.iei.ratallert.services.googleService.api.response.GAResponse;
import com.iei.ratallert.services.lifeQuality.database.entities.Stat;
import com.iei.ratallert.services.lifeQuality.model.LifeQualitySensorDataModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ResourceBundle;

@Log4j2
@Service
public class RatAlertApp extends ActionsSdkApp {

    @ForIntent("home_conditions")
    public GAResponse homeConditions() {
        log.info("home_conditions intent start.");
        //ResponseBuilder responseBuilder = getResponseBuilder(request);
        String homeConditionPattern = "Current room temperature is %s degrees. The humidity is %s percents. The air quality is %s PPT that means %s";

        RestTemplate restTemplate = new RestTemplate();
        LifeQualitySensorDataModel lifeQualityData = restTemplate.getForObject("http://94.158.155.196:82" + "/lifeQuality",
                LifeQualitySensorDataModel.class);
        Stat curStat = new Stat(lifeQualityData);

        String homeCondition = String.format(homeConditionPattern,
                curStat.getRoomTemperature(),
                curStat.getRoomHumidity(),
                curStat.getRoomAirQualityPpmValue(),
                curStat.getRoomAirQualityLevel());

        GAResponse response = GAResponse.builder()
                .prompt(
                        GAPrompt.builder()
                                .firstSimple(GAFirstSimple.builder()
                                        .speech(homeCondition).build())
                                .build())
                .build();

        log.info("home_conditions intent end.");
        return response;
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
