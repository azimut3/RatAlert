package com.iei.ratallert.database.controllers;

import com.iei.ratallert.database.entities.Stat;
import com.iei.ratallert.database.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    @Autowired
    StatsRepository<Stat> statsRepository;

    public void save(Stat stat) {
        statsRepository.saveAndFlush(stat);
    }
}
