package com.iei.ratallert.logic;

import lombok.Data;

import java.io.Serializable;

@Data
public class LifeQualityData implements Serializable {
    private Double airQualityPpm;
    private Double temperature;
    private Double humidity;
}
