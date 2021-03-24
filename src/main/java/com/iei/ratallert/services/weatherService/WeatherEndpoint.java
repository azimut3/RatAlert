package com.iei.ratallert.services.weatherService;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/weather/v1")
public class WeatherEndpoint {

    @GetMapping("/currentWeather")
    public CurrentWeatherDataResponse getCurrentWeatherForecast() {

        CurrentWeatherDataResponse response = OpenWeatherService.getCurrentForecast();
        return response;
    }
}
