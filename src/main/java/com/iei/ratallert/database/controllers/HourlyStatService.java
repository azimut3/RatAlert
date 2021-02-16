package com.iei.ratallert.database.controllers;

import com.iei.ratallert.database.entities.HourlyStat;
import com.iei.ratallert.database.entities.Stat;
import com.iei.ratallert.database.repository.HourlyStatRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class HourlyStatService {
    @Autowired
    HourlyStatRepository hourlyStatRepository;

    public void save(HourlyStat stat) {
        log.info("Saving stat: " + stat);
        hourlyStatRepository.saveAndFlush(stat);
    }

    public void saveAll(List<HourlyStat> statList) {
        log.info("Saving stat: " + statList);
        hourlyStatRepository.saveAll(statList);
    }

    public void deleteAll(List<HourlyStat> stats) {
        log.info("deleting stats: " + stats);
        hourlyStatRepository.deleteAll(stats);
    }

    public List<HourlyStat> getAll() {
        return hourlyStatRepository.findAll();
    }

    public List<HourlyStat> getLastHourData(LocalDateTime currentTime) {
        return hourlyStatRepository.findAllByCreationDateIsBetween(currentTime.minusMinutes(5), currentTime);
    }

    public List<HourlyStat> getAllStatsSorted() {
        return hourlyStatRepository.findAllAndOrderByCreationDate();
    }

    public List<HourlyStat> findAllAndOrderByCreationDate() {
        return hourlyStatRepository.findAllAndOrderByCreationDate();
    }
}
