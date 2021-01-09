package com.iei.ratallert.database.entities;

import com.iei.ratallert.logic.LifeQualityData;
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

    private Double roomAirQualityPpmValue;
    @Setter(AccessLevel.NONE)
    private String roomAirQualityLevel;
    @Setter(AccessLevel.NONE)

    private Date creationDate;

    public Stat(){
        if(creationDate == null){
            creationDate = new Date();
        }

        if(roomAirQualityLevel == null && roomAirQualityPpmValue != null) {
            roomAirQualityLevel = getAirQualityLevel(roomAirQualityPpmValue);
        }
    }

    public Stat(LifeQualityData lifeQualityData) {
        this.roomHumidity = lifeQualityData.getHumidity();
        this.roomTemperature = lifeQualityData.getTemperature();
        this.roomAirQualityPpmValue = lifeQualityData.getAirQualityPpm();
        this.roomAirQualityLevel = getAirQualityLevel(roomAirQualityPpmValue);
    }

    public static String getAirQualityLevel(Double airQualityPpmValue){

        if(airQualityPpmValue <= 50.){
            return "GOOD";
        } else if(airQualityPpmValue <= 100.){
            return "Moderate";
        } else if(airQualityPpmValue <= 150.){
            return "Unhealthy for SG";
        } else if(airQualityPpmValue <= 200.){
            return "Unhealthy";
        }else if(airQualityPpmValue <= 300.){
            return "Very Unhealthy";
        } else {
            return "Hazardous";
        }
    }
}
