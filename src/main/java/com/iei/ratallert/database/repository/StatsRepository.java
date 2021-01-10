package com.iei.ratallert.database.repository;

import com.iei.ratallert.database.entities.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface StatsRepository <T extends Stat> extends JpaRepository<Stat, Long> {

    List<T> getLastHourData(Date curDate);

    List<T> getListWhereCreationDateLessThan(Date curDate);
}
