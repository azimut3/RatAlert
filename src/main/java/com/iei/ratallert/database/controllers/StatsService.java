package com.iei.ratallert.database.controllers;

import com.iei.ratallert.database.entities.Stat;
import com.iei.ratallert.database.repository.StatsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class StatsService {

    @Autowired
    StatsRepository<Stat> statsRepository;

    public void save(Stat stat) {
        log.debug("Saving stat: " + stat);
        statsRepository.saveAndFlush(stat);
    }
}
