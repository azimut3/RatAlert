package com.iei.ratallert.services.weatherService.currentWeatherDataModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "snow.1h",
        "snow.3h"
})
@Getter
@Setter
@Accessors(chain = true)
public class Snow {
    /**
     * Snow volume for the last 3 hours, mm
     */
    @JsonProperty("snow.1h")
    private Integer snowForHour;
    /**
     * Snow volume for the last 3 hours, mm
     */
    @JsonProperty("snow.3h")
    private Integer snowForThreeHours;
}
