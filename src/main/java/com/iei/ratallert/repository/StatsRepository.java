package com.iei.ratallert.repository;

import com.iei.ratallert.data.Stat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatsRepository <T extends Stat> extends JpaRepository<Stat, Long> {

    T findByName(String name);

}
