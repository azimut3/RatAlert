package com.iei.ratallert.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Stat {
    @Id
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

}
