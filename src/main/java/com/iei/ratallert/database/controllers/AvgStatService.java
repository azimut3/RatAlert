package com.iei.ratallert.database.controllers;

import com.iei.ratallert.database.entities.Stat;
import com.iei.ratallert.database.repository.AvgStatsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class AvgStatService {
    @Autowired
    AvgStatsRepository avgStatsRepository;

    public void save(Stat stat) {
        log.info("Saving stat: " + stat);
        avgStatsRepository.saveAndFlush(stat);
    }

    public void deleteAll(List<Stat> stats) {
        log.info("deleting stats: " + stats);
        avgStatsRepository.deleteAll(stats);
    }

    public List<Stat> getAll() {
        return avgStatsRepository.findAll();
    }

    public List<Stat> getLastHourData(LocalDateTime currentTime) {
        return avgStatsRepository.findAllByCreationDateIsBetween(currentTime.minusMinutes(5), currentTime);
    }
}
