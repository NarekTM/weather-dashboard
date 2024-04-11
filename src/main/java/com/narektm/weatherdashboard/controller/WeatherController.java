package com.narektm.weatherdashboard.controller;

import com.narektm.weatherdashboard.service.WeatherService;
import com.narektm.weatherdashboard.service.country.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WeatherController {

    private static final String WEATHER = "weather";
    private static final String COUNTRIES = "countries";

    private final WeatherService weatherService;

    private final CountryService countryService;

    public WeatherController(WeatherService weatherService,
                             CountryService countryService) {
        this.weatherService = weatherService;
        this.countryService = countryService;
    }

    @GetMapping("/")
    public String weatherDashboard(Model model) {
        model.addAttribute(COUNTRIES, countryService.getAllActiveCountries());

        return WEATHER;
    }

    @GetMapping("/{cityName}/current")
    public String getCurrentWeather(@PathVariable String cityName, Model model) {
        model.addAttribute(COUNTRIES, countryService.getAllActiveCountries());
        model.addAttribute("weatherData", weatherService.getCurrentWeather(cityName));

        return WEATHER;
    }

    @GetMapping("/{cityName}/forecast")
    public String getWeatherForecast(@PathVariable String cityName, Model model) {
        model.addAttribute(COUNTRIES, countryService.getAllActiveCountries());
        model.addAttribute("weatherForecast", weatherService.getWeatherForecast(cityName));

        return WEATHER;
    }
}
