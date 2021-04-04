package com.iei.ratallert.services.drugsService.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @GetMapping("/drugUnitType")
    public  List<DrugUnitWrapper> getDrugUnitTypes() {
        Drug.Unit[] drugUnitList = Drug.Unit.values();
        List<DrugUnitWrapper> drugUnitWrapperList = new ArrayList<>();
        for(var unit : drugUnitList){
            drugUnitWrapperList.add(new DrugUnitWrapper(unit.getLabel(), unit.getValue()));
        }
        log.info(drugUnitList);
        return drugUnitWrapperList;
    }

    @Data
    @AllArgsConstructor
    private class DrugUnitWrapper{
        public String label;
        public String value;
    }
}
