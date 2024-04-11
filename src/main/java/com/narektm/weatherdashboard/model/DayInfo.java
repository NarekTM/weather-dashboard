package com.narektm.weatherdashboard.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayInfo {

    @JsonProperty("maxtemp_c")
    private Double maxTempC;

    @JsonProperty("maxtemp_f")
    private Double maxTempF;

    @JsonProperty("mintemp_c")
    private Double minTempC;

    @JsonProperty("mintemp_f")
    private Double minTempF;

    @JsonProperty("avgtemp_c")
    private Double avgTempC;

    @JsonProperty("avgtemp_f")
    private Double avgTempF;

    @JsonProperty("maxwind_mph")
    private Double maxWindMph;

    @JsonProperty("maxwind_kph")
    private Double maxWindKph;

    @JsonProperty("totalprecip_mm")
    private Double totalPrecipMm;

    @JsonProperty("totalprecip_in")
    private Double totalPrecipIn;

    @JsonProperty("totalsnow_cm")
    private Double totalSnowCm;

    @JsonProperty("avgvis_km")
    private Double avgVisKm;

    @JsonProperty("avgvis_miles")
    private Double avgVisMiles;

    @JsonProperty("avghumidity")
    private Integer avgHumidity;

    @JsonProperty("daily_will_it_rain")
    private Integer dailyWillItRain;

    @JsonProperty("daily_chance_of_rain")
    private Integer dailyChanceOfRain;

    @JsonProperty("daily_will_it_snow")
    private Integer dailyWillItSnow;

    @JsonProperty("daily_chance_of_snow")
    private Integer dailyChanceOfSnow;

    @JsonProperty("uv")
    private Double uv;
}
