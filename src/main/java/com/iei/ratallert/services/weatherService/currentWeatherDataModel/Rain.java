package com.iei.ratallert.services.weatherService.currentWeatherDataModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "rain.1h",
        "rain.3h"
})
@Getter
@Setter
@Accessors(chain = true)
public class Rain {
    /**
     * Snow volume for the last 3 hours, mm
     */
    @JsonProperty("rain.1h")
    private Integer rainForHour;
    /**
     * Snow volume for the last 3 hours, mm
     */
    @JsonProperty("rain.3h")
    private Integer rainForThreeHours;
}
