package com.iei.ratallert.database.repository;

import com.iei.ratallert.database.entities.Stat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatsRepository <T extends Stat> extends JpaRepository<Stat, Long> {

}
