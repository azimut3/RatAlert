package com.iei.ratallert.services.lifeQuality.database.entities;

import com.iei.ratallert.services.lifeQuality.model.LifeQualitySensorDataModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.math3.util.Precision;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Data
@Accessors(chain = true)
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

    protected LocalDateTime creationDate;

    public Stat(){
        if(creationDate == null){
            creationDate = LocalDateTime.now();
        }
    }

    public Stat(LifeQualitySensorDataModel lifeQualityData) {
        this.roomHumidity = Precision.round(lifeQualityData.getHumidity(), 0);
        this.roomTemperature = Precision.round(lifeQualityData.getTemperature(), 1);
        this.roomAirQualityPpmValue = Precision.round(lifeQualityData.getAirQualityPpm(), 0);
        this.roomAirQualityLevel = getAirQualityLevel(roomAirQualityPpmValue);

        if(creationDate == null){
            creationDate = LocalDateTime.now(ZoneOffset.UTC);
        }
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

    public void setRoomAirQualityPpmValue(Double roomAirQualityPpmValue) {
        this.roomAirQualityPpmValue = roomAirQualityPpmValue;
        if(roomAirQualityLevel == null && roomAirQualityPpmValue != null) {
            roomAirQualityLevel = getAirQualityLevel(roomAirQualityPpmValue);
        }
    }
}
