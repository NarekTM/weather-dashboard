package com.narektm.weatherdashboard.service;


import com.narektm.weatherdashboard.exception.WeatherInfoException;
import com.narektm.weatherdashboard.model.CurrentWeather;
import com.narektm.weatherdashboard.model.CurrentWeatherModel;
import com.narektm.weatherdashboard.model.DayInfo;
import com.narektm.weatherdashboard.model.WeatherApiResponse;
import com.narektm.weatherdashboard.model.WeatherForecast;
import com.narektm.weatherdashboard.model.WeatherForecastDay;
import com.narektm.weatherdashboard.model.WeatherForecastModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    /**
     * A response of the forecast URL contains both current and forecast weather info.
     * The URL is configured to request up to 3 days of weather forecast,
     * aligning with the free tier's limitations of the API.
     */
    private static final String URL_PATTERN = "https://api.weatherapi.com/v1/forecast.json?q=%s&days=3&key=%s";

    private final String apiKey;

    private final RestClient restClient;

    public WeatherService(RestClient restClient,
                          @Value("${weather.api-key}") String apiKey) {
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public CurrentWeatherModel getCurrentWeather(String city) {
        ResponseEntity<WeatherApiResponse> result = getWeatherApiResponse(city);

        CurrentWeather currentWeather = Optional.ofNullable(result.getBody())
                .map(WeatherApiResponse::getCurrentWeather)
                .orElseThrow(() -> new WeatherInfoException(String.format("No current weather info for city %s", city)));

        return getCurrentWeatherModel(city, currentWeather);
    }

    public List<WeatherForecastModel> getWeatherForecast(String city) {
        ResponseEntity<WeatherApiResponse> result = getWeatherApiResponse(city);

        List<WeatherForecastDay> weatherForecastDays = Optional.ofNullable(result.getBody())
                .map(WeatherApiResponse::getWeatherForecast)
                .map(WeatherForecast::getWeatherForecastDays)
                .orElseThrow(() -> new WeatherInfoException(String.format("No weather forecast info for city %s", city)));

        return weatherForecastDays.stream()
                .map(wfd -> getWeatherForecastModel(city, wfd.getDate(), wfd.getDayInfo()))
                .toList();
    }

    private ResponseEntity<WeatherApiResponse> getWeatherApiResponse(String city) {
        return restClient.get()
                .uri(String.format(URL_PATTERN, city, apiKey))
                .retrieve()
                .toEntity(WeatherApiResponse.class);
    }

    private static CurrentWeatherModel getCurrentWeatherModel(String city, CurrentWeather currentWeather) {
        CurrentWeatherModel currentWeatherModel = new CurrentWeatherModel();
        currentWeatherModel.setCity(city);
        currentWeatherModel.setUv(currentWeather.getUv());
        currentWeatherModel.setGustKph(currentWeather.getGustKph());
        currentWeatherModel.setGustMph(currentWeather.getGustMph());
        currentWeatherModel.setTemperature(currentWeather.getTempC());
        return currentWeatherModel;
    }

    private static WeatherForecastModel getWeatherForecastModel(String city, LocalDate date, DayInfo dayInfo) {
        WeatherForecastModel weatherForecastModel = new WeatherForecastModel();

        weatherForecastModel.setCity(city);
        weatherForecastModel.setDate(date);
        weatherForecastModel.setAvgTempC(dayInfo.getAvgTempC());
        weatherForecastModel.setMinTempC(dayInfo.getMinTempC());
        weatherForecastModel.setMaxTempC(dayInfo.getMaxTempC());
        weatherForecastModel.setAvgTempF(dayInfo.getAvgTempF());
        weatherForecastModel.setMinTempF(dayInfo.getMinTempF());
        weatherForecastModel.setMaxTempF(dayInfo.getMaxTempF());

        return weatherForecastModel;
    }
}
