package com.narektm.weatherdashboard.controller;

import com.narektm.weatherdashboard.dto.CityDto;
import com.narektm.weatherdashboard.service.GeoService;
import com.narektm.weatherdashboard.service.city.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeoController {

    private final CityService cityService;

    private final GeoService geoService;

    public GeoController(CityService cityService, GeoService geoService) {
        this.cityService = cityService;
        this.geoService = geoService;
    }

    @GetMapping("/{countryId}/cities")
    public List<CityDto> getCitiesByCountry(@PathVariable Long countryId) {
        return cityService.getCitiesByCountryId(countryId);
    }

    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void fetchAndStoreCountries() {
        geoService.fetchAndStoreCountries();
    }

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void fetchAndStoreCities() {
        geoService.fetchAndStoreCities();
    }
}
