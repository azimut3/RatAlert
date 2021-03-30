package com.iei.ratallert.services.drugsService.database;

import com.iei.ratallert.services.lifeQuality.database.entities.HourlyStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}
