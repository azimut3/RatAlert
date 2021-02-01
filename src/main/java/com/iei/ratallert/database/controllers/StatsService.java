package com.iei.ratallert.database.controllers;

import com.iei.ratallert.database.entities.Stat;
import com.iei.ratallert.database.repository.StatsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class StatsService {

    @Autowired
    StatsRepository statsRepository;

    public void save(Stat stat) {
        log.info("Saving stat: " + stat);
        statsRepository.saveAndFlush(stat);
    }

    public List<Stat> getAll() {
        return statsRepository.findAll();
    }

    public List<Stat> getLastHourData(LocalDateTime currentTime) {
        return statsRepository.findAllByCreationDateIsBetween(currentTime.minusMinutes(5), currentTime);
    }
}
