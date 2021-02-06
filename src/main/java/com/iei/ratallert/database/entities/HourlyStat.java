package com.iei.ratallert.database.entities;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class HourlyStat extends Stat{

    public HourlyStat setCreationDate(LocalDateTime dateTime){
        this.creationDate = dateTime;
        return this;
    }
}
