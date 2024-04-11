package com.narektm.weatherdashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentWeather {

    @JsonProperty("temp_c")
    private Double tempC;

    @JsonProperty("temp_f")
    private Double tempF;

    @JsonProperty("is_day")
    private Integer isDay;

    @JsonProperty("wind_mph")
    private Double windMph;

    @JsonProperty("wind_kph")
    private Double windKph;

    @JsonProperty("wind_degree")
    private Integer windDegree;

    @JsonProperty("wind_dir")
    private String windDir;

    @JsonProperty("pressure_mb")
    private Double pressureMb;

    @JsonProperty("pressure_in")
    private Double pressureIn;

    @JsonProperty("precip_mm")
    private Double precipMm;

    @JsonProperty("precip_in")
    private Double precipIn;

    @JsonProperty("humidity")
    private Integer humidity;

    @JsonProperty("cloud")
    private Integer cloud;

    @JsonProperty("feelslike_c")
    private Double feelslikeC;

    @JsonProperty("feelslike_f")
    private Double feelslikeF;

    @JsonProperty("vis_km")
    private Double visKm;

    @JsonProperty("vis_miles")
    private Double visMiles;

    @JsonProperty("uv")
    private Double uv;

    @JsonProperty("gust_mph")
    private Double gustMph;

    @JsonProperty("gust_kph")
    private Double gustKph;

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "tempC=" + tempC +
                ", tempF=" + tempF +
                ", isDay=" + isDay +
                ", windMph=" + windMph +
                ", windKph=" + windKph +
                ", windDegree=" + windDegree +
                ", windDir='" + windDir + '\'' +
                ", pressureMb=" + pressureMb +
                ", pressureIn=" + pressureIn +
                ", precipMm=" + precipMm +
                ", precipIn=" + precipIn +
                ", humidity=" + humidity +
                ", cloud=" + cloud +
                ", feelslikeC=" + feelslikeC +
                ", feelslikeF=" + feelslikeF +
                ", visKm=" + visKm +
                ", visMiles=" + visMiles +
                ", uv=" + uv +
                ", gustMph=" + gustMph +
                ", gustKph=" + gustKph +
                '}';
    }
}
