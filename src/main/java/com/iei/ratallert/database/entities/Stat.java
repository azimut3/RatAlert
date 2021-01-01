package com.iei.ratallert.database.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Stat {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Double roomHumidity;
    private Double roomTemperature;
    private Double roomAirQualityValue;
    private String roomAirQualityLevel;
    @Setter(AccessLevel.NONE)
    private Date creationDate;

    public Stat(){
        if(creationDate == null){
            creationDate = new Date();
        }
    }

    public Stat(Double roomHumidity, Double roomTemperature, Double roomAirQualityValue, String roomAirQualityLevel) {
        this.roomHumidity = roomHumidity;
        this.roomTemperature = roomTemperature;
        this.roomAirQualityValue = roomAirQualityValue;
        this.roomAirQualityLevel = roomAirQualityLevel;
    }
}
