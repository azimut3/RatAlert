package com.iei.ratallert.services.weatherService;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Log4j2
@RestController
@RequestMapping("/api/weather/v1")
public class WeatherEndpoint {
    public static CurrentWeatherDataResponse responseInstance;

    @GetMapping("/currentWeather")
    public CurrentWeatherDataResponse getCurrentWeatherForecast() {

        if(responseInstance == null){
            responseInstance = OpenWeatherService.getCurrentForecast();
        }
        if((responseInstance.getFetchedDate().getMinute() + LocalDateTime.now(ZoneOffset.UTC).getMinute()) >= 10){
            responseInstance = OpenWeatherService.getCurrentForecast();
        }

        return responseInstance;
    }
}
