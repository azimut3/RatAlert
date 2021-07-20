package com.iei.ratallert.services.googleService;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RestController
@RequestMapping("/api/google/v1")
public class GoogleApi {

    @PostMapping(value = "/home")
    @ResponseBody
    public String testIncome(@RequestBody String request) {
        log.info("Incoming JSON: " + request);
        return "success data fetch";
    }
}
