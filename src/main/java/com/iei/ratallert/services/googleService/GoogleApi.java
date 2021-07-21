package com.iei.ratallert.services.googleService;

import com.iei.ratallert.services.googleService.api.generic.GAFirstSimple;
import com.iei.ratallert.services.googleService.api.generic.GAPrompt;
import com.iei.ratallert.services.googleService.api.request.GARequest;
import com.iei.ratallert.services.googleService.api.response.GAResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/google/v1")
public class GoogleApi {

    @Autowired
    RatAlertApp ratAlertApp;

    @PostMapping(value = "/home", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {"application/json"})
    @ResponseBody
    public GAResponse testIncome(@RequestBody GARequest request, @RequestHeader Map<String, String> headers) {
        String intentName = request.getHandler().getName();

        switch (intentName) {
            case "home_conditions":
                return ratAlertApp.homeConditions();
            default:
                return  GAResponse.builder()
                        .prompt(
                                GAPrompt.builder()
                                        .firstSimple(GAFirstSimple.builder()
                                                .speech("No such intent found").build())
                                        .build()).build();
        }

    }

    @GetMapping("/")
    String serveAck() {
        return "App is listening but requires valid POST request to respond with Action response.";
    }
}
