package com.iei.ratallert.services.googleService;

import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import com.google.api.services.actions_fulfillment.v2.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Log4j2
@Service
public class RatAlertApp extends DialogflowApp {
    @ForIntent("home_conditions")
    public ActionResponse homeConditions(ActionRequest request) {
        log.info("home_conditions intent start.");
        ResponseBuilder responseBuilder = getResponseBuilder(request);
        String homeCondition = "Current weather condition is ...";

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
