package com.iei.ratallert.services.drugsService.database;

import com.iei.ratallert.services.lifeQuality.database.controllers.HourlyStatService;
import com.iei.ratallert.services.lifeQuality.database.entities.HourlyStat;
import com.iei.ratallert.services.lifeQuality.database.repository.HourlyStatRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class DrugService {

    @Autowired
    DrugRepository drugRepository;

    public void save(Drug drug) {
        log.info("Saving drug: " + drug);
        drugRepository.saveAndFlush(drug);
    }

    public List<Drug> getAllDrugs(){
        List<Drug> allDrugs = drugRepository.findAll();

        return allDrugs;
    }

    public void deleteDrugs(List<Long> idsToDelete) {
        List<Drug> drugsToDelete = drugRepository.findAllById(idsToDelete);
        if(!drugsToDelete.isEmpty()){
            drugRepository.deleteInBatch(drugsToDelete);
        }
    }
}
