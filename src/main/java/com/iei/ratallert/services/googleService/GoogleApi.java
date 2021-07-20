package com.iei.ratallert.services.googleService;

import com.google.actions.api.DefaultApp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @PostMapping(value = "/home")
    @ResponseBody
    public String testIncome(@RequestBody String requestBody,  @RequestHeader HttpHeaders headers) {
        try {
            log.info(requestBody);
            log.info(headers.getContentType().getParameters());
            CompletableFuture app = ratAlertApp.handleRequest(requestBody, headers.getContentType().getParameters());
            String jsonResponse = app.get().toString();
            log.info("Generated json = {}", jsonResponse);

            return jsonResponse;
        } catch (InterruptedException e) {
            log.error(e);
            return e.toString();
        } catch (ExecutionException e) {
            log.error(e);
            return e.toString();
        }
    }
}
