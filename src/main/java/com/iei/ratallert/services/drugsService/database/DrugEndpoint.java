package com.iei.ratallert.services.drugsService.database;

import com.iei.ratallert.services.lifeQuality.database.controllers.HourlyStatService;
import com.iei.ratallert.services.lifeQuality.database.entities.HourlyStat;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/drugs/v1")
public class DrugEndpoint {

    @Autowired
    DrugService drugService;

    @PostMapping("/create")
    public String postDrug(Drug newDrug) {
        Drug drug = newDrug;
        drugService.save(drug);
        return "success";
    }
}
