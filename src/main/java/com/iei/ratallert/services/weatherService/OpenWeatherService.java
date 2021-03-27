package com.iei.ratallert.services.weatherService;

import org.springframework.web.client.RestTemplate;

public class OpenWeatherService {
    private static String API_KEY = System.getProperty("OPEN_WEATHEP_API_KEY") ;
    private static String CITY_ID = "698740";
    private static String UNITS = "metric";
    private static String CURRENT_WEATHER = "api.openweathermap.org/data/2.5/weather?Id={cityId}&appid={API_key}";
    private static String TEST_CURRENT_WEATHER = "http://api.openweathermap.org/data/2.5/weather?id=698740&appid=fe901b4ce965424b15397e529b828d91&lang=ua&units=metric";

    public static CurrentWeatherDataResponse getCurrentForecast(){
        RestTemplate restTemplate = new RestTemplate();

        CurrentWeatherDataResponse currentWeatherDataResponse = restTemplate
                .getForObject(TEST_CURRENT_WEATHER, CurrentWeatherDataResponse.class);

        return currentWeatherDataResponse;
    }
}
