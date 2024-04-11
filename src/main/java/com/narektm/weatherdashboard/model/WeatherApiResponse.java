package com.narektm.weatherdashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherApiResponse {

    @JsonProperty("current")
    private CurrentWeather currentWeather;

    @JsonProperty("forecast")
    private WeatherForecast weatherForecast;
}
