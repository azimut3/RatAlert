package com.iei.ratallert.services.lifeQuality.database.repository;

import com.iei.ratallert.services.lifeQuality.database.entities.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AvgStatsRepository  extends JpaRepository<Stat, Long> {

    List<Stat> findAllByCreationDateIsBetween(LocalDateTime moreThanDate, LocalDateTime lessThanDate);

}
