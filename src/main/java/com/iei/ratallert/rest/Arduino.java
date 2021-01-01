package com.iei.ratallert.rest;

import com.iei.ratallert.database.controllers.StatsService;
import com.iei.ratallert.database.entities.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Arduino {
    @Autowired
    StatsService statsService;

    @PostMapping(path = "/v1/api/esp")
    public void getData(Stat stat){
        statsService.save(stat);
    }
}
