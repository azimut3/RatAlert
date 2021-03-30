package com.iei.ratallert.services.lifeQuality.database.repository;

import com.iei.ratallert.services.lifeQuality.database.entities.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface StatsRepository extends JpaRepository<Stat, Long> {

    List<Stat> findAllByCreationDateIsBetween(LocalDateTime moreThanDate, LocalDateTime lessThanDate);

    @Query(value = "SELECT stat FROM Stat stat ORDER BY stat.creationDate")
    List<Stat> findAllAndOrderByCreationDate();
}
