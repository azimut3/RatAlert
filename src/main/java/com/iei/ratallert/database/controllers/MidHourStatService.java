package com.iei.ratallert.database.controllers;

import com.iei.ratallert.database.entities.Stat;
import com.iei.ratallert.database.repository.StatsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class MidHourStatService {

    @Autowired
    StatsRepository statsRepository;

    public void save(Stat stat) {
        log.info("Saving stat: " + stat);
        statsRepository.saveAndFlush(stat);
    }

    public void deleteAll(List<Stat> stats) {
        log.info("deleting stats: " + stats);
        statsRepository.deleteAll(stats);
    }

    public List<Stat> getAll() {
        return statsRepository.findAll();
    }

    public List<Stat> getLastHourData(LocalDateTime currentTime) {
        return statsRepository.findAllByCreationDateIsBetween(currentTime.minusMinutes(5), currentTime);
    }

    public List<Stat> getAllStatsSorted() {
        return statsRepository.findAll(Sort.by("creationDate"));
    }
}
