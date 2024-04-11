package com.narektm.weatherdashboard.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WeatherForecastModel {

    private String city;

    private LocalDate date;

    private Double maxTempC;

    private Double maxTempF;

    private Double minTempC;

    private Double minTempF;

    private Double avgTempC;

    private Double avgTempF;
}
