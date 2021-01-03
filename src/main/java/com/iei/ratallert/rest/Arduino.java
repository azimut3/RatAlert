package com.iei.ratallert.rest;

import com.iei.ratallert.database.controllers.StatsService;
import com.iei.ratallert.database.entities.HourlyStat;
import com.iei.ratallert.database.entities.Stat;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class Arduino {
    @Autowired
    StatsService statsService;

    @GetMapping(path = "/v1/api/esp")
    public String test(){
        return "Success";
    }

    @PostMapping(path = "/v1/api/esp")
    public String getData(@RequestBody HourlyStat stat){
        log.info("Getting data from endpoint: " + stat);
        statsService.save(stat);

        return "Success";
    }
}
