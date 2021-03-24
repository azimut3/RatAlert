package com.iei.ratallert.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LifeQualitySensorDataModel implements Serializable {
    private Double airQualityPpm;
    private Double temperature;
    private Double humidity;
}
