package com.narektm.weatherdashboard.controller;

import com.narektm.weatherdashboard.dto.CityDto;
import com.narektm.weatherdashboard.service.city.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeoController {

    private final CityService cityService;

    public GeoController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{countryId}/cities")
    public List<CityDto> getCitiesByCountry(@PathVariable Long countryId) {
        return cityService.getCitiesByCountryId(countryId);
    }
}
