package com.iei.ratallert.database.entities;

import com.iei.ratallert.model.LifeQualitySensorDataModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class HourlyStat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double roomHumidity;
    private Double roomTemperature;

    private Double roomAirQualityPpmValue;
    @Setter(AccessLevel.NONE)
    private String roomAirQualityLevel;
    @Setter(AccessLevel.NONE)

    protected LocalDateTime creationDate;

    public HourlyStat() {
        if (creationDate == null) {
            creationDate = LocalDateTime.now();
        }
    }

    public HourlyStat(LifeQualitySensorDataModel lifeQualityData) {
        this.roomHumidity = lifeQualityData.getHumidity();
        this.roomTemperature = lifeQualityData.getTemperature();
        this.roomAirQualityPpmValue = lifeQualityData.getAirQualityPpm();
        this.roomAirQualityLevel = getAirQualityLevel(roomAirQualityPpmValue);

        if (creationDate == null) {
            creationDate = LocalDateTime.now();
        }
    }

    public static String getAirQualityLevel(Double airQualityPpmValue) {

        if (airQualityPpmValue <= 50.) {
            return "GOOD";
        } else if (airQualityPpmValue <= 100.) {
            return "Moderate";
        } else if (airQualityPpmValue <= 150.) {
            return "Unhealthy for SG";
        } else if (airQualityPpmValue <= 200.) {
            return "Unhealthy";
        } else if (airQualityPpmValue <= 300.) {
            return "Very Unhealthy";
        } else {
            return "Hazardous";
        }
    }

    public void setRoomAirQualityPpmValue(Double roomAirQualityPpmValue) {
        this.roomAirQualityPpmValue = roomAirQualityPpmValue;
        if (roomAirQualityLevel == null && roomAirQualityPpmValue != null) {
            roomAirQualityLevel = getAirQualityLevel(roomAirQualityPpmValue);
        }
    }


    public HourlyStat setCreationDate(LocalDateTime dateTime) {
        this.creationDate = dateTime;
        return this;
    }
}
