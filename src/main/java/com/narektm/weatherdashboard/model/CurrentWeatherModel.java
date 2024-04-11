package com.narektm.weatherdashboard.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentWeatherModel {

    private String city;

    private Double temperature;

    private Double uv;

    private Double gustMph;

    private Double gustKph;
}
