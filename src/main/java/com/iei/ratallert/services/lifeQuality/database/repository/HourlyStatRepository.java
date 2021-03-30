package com.iei.ratallert.services.lifeQuality.database.repository;

import com.iei.ratallert.services.lifeQuality.database.entities.HourlyStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface HourlyStatRepository extends JpaRepository<HourlyStat, Long> {

    List<HourlyStat> findAllByCreationDateIsBetweenOrderByCreationDate(LocalDateTime moreThanDate, LocalDateTime lessThanDate);

    @Query(value = "SELECT * FROM hourly_stat ORDER BY creation_date", nativeQuery = true)
    List<HourlyStat> findAllAndOrderByCreationDate();
}