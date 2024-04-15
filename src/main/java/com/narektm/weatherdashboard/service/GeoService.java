package com.narektm.weatherdashboard.service;

import com.narektm.weatherdashboard.entity.CityEntity;
import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.service.city.CityDataFetcher;
import com.narektm.weatherdashboard.service.city.CityService;
import com.narektm.weatherdashboard.service.country.CountryDataFetcher;
import com.narektm.weatherdashboard.service.country.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoService.class);

    private final CountryDataFetcher countryDataFetcher;

    private final CountryService countryService;

    private final CityDataFetcher cityDataFetcher;

    private final CityService cityService;

    public GeoService(CountryDataFetcher countryDataFetcher,
                      CountryService countryService,
                      CityDataFetcher cityDataFetcher,
                      CityService cityService) {
        this.countryDataFetcher = countryDataFetcher;
        this.countryService = countryService;
        this.cityDataFetcher = cityDataFetcher;
        this.cityService = cityService;
    }

    public void fetchAndStoreCountries() {
        LOGGER.info("Executing countries fetching and storing...");
        List<CountryEntity> countries = countryDataFetcher.fetch();
        countryService.checkAndSave(countries);
        LOGGER.info("Countries fetched and stored successfully.");
    }

    public void fetchAndStoreCities() {
        LOGGER.info("Executing cities fetching and storing...");
        countryService.getAllCountries().forEach(country -> {
            List<CityEntity> cities = cityDataFetcher.fetch(country);
            cityService.checkAndSave(cities, country);
        });
        LOGGER.info("Cities fetched and stored successfully.");
    }
}
