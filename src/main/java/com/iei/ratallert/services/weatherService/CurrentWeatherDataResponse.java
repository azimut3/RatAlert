package com.iei.ratallert.services.weatherService;

import com.fasterxml.jackson.annotation.*;
import com.iei.ratallert.services.weatherService.currentWeatherDataModel.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "coord",
        "weather",
        "base",
        "main",
        "visibility",
        "wind",
        "clouds",
        "dt",
        "sys",
        "timezone",
        "id",
        "name",
        "cod"
})

public class CurrentWeatherDataResponse {
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("weather")
    private List<Weather> weather = null;
    @JsonProperty("base")
    private String base;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("visibility")
    private Integer visibility;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("clouds")
    private Clouds clouds;
    @JsonProperty("rain")
    private Rain rain;
    @JsonProperty("snow")
    private Snow snow;
    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("sys")
    private Sys sys;
    @JsonProperty("timezone")
    private Integer timezone;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cod")
    private Integer cod;

    private LocalDateTime fetchedDate;

    public CurrentWeatherDataResponse(){
        this.fetchedDate = LocalDateTime.now(ZoneOffset.UTC);
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
