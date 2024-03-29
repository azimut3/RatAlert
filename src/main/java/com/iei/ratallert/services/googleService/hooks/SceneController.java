package com.example.googleactionswebhooks.hooks;

import com.iei.ratallert.services.googleService.api.generic.*;
import com.iei.ratallert.services.googleService.api.request.GARequest;
import com.iei.ratallert.services.googleService.api.response.GAResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/scene")
@Slf4j
@RestController
public class SceneController {

    @RequestMapping(path="/", method={RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GAResponse nextScene(@RequestBody GARequest request){
        log.info("Next scene BEGIN");
        log.info("Request: " + request);

        GAResponse response = new GAResponse(
                new GAPrompt(false,
                        new GAFirstSimple("This text comes from the server! You slay the dragon. Hi everyboy!",null),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null),
                new GAScene(null,null,null, new GANextScene("YOU_WIN")),
                request.getSession(), request.getUser(), request.getHome(),
                request.getDevice(), new GAExpected(null, null));

        log.info("Next scene END Response: "+ response);
        return response;
    }
}


