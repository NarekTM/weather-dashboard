package com.narektm.weatherdashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherForecast {

    @JsonProperty("forecastday")
    private List<WeatherForecastDay> weatherForecastDays = new ArrayList<>();
}
