package com.narektm.weatherdashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WeatherForecastDay {

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("date_epoch")
    private Long dateEpoch;

    @JsonProperty("day")
    private DayInfo dayInfo;
}
