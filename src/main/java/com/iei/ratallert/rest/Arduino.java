package com.iei.ratallert.rest;

import com.iei.ratallert.database.controllers.StatsService;
import com.iei.ratallert.database.entities.Stat;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class Arduino {
    @Autowired
    StatsService statsService;

    @PostMapping(path = "/v1/api/esp")
    public void getData(Stat stat){
        log.debug("Getting data from endpoint: " + stat);
        statsService.save(stat);
    }
}
