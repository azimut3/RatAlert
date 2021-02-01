package com.iei.ratallert.database.repository;

import com.iei.ratallert.database.entities.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public interface StatsRepository extends JpaRepository<Stat, Long> {

    List<Stat> findAllByCreationDateIsBetween(LocalDateTime moreThanDate, LocalDateTime lessThanDate);

}
