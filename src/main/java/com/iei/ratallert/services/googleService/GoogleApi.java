package com.iei.ratallert.services.googleService;

import com.google.actions.api.DefaultApp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping("/api/google/v1")
public class GoogleApi {

    @Autowired
    RatAlertApp ratAlertApp;

    @PostMapping(value = "/home", consumes = MediaType.APPLICATION_JSON_VALUE, produces = { "application/json" })
    @ResponseBody
    public String testIncome(@RequestBody String requestBody,  @RequestHeader Map<String, String> headers) {
        try {
            log.info(requestBody);
            return ratAlertApp.handleRequest(requestBody, headers).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e);
            return e.toString();
        }
    }

    @GetMapping("/")
    String serveAck() {
        return "App is listening but requires valid POST request to respond with Action response.";
    }
}
