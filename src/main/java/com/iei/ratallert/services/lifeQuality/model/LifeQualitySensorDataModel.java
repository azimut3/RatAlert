package com.iei.ratallert.services.lifeQuality.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LifeQualitySensorDataModel implements Serializable {
    private Double airQualityPpm;
    private Double temperature;
    private Double humidity;
}
