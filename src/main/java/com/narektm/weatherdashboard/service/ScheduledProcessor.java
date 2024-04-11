package com.narektm.weatherdashboard.service;

import com.narektm.weatherdashboard.entity.CityEntity;
import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.service.city.CityDataFetcher;
import com.narektm.weatherdashboard.service.city.CityService;
import com.narektm.weatherdashboard.service.country.CountryDataFetcher;
import com.narektm.weatherdashboard.service.country.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledProcessor.class);

    private final CountryDataFetcher countryDataFetcher;

    private final CountryService countryService;

    private final CityDataFetcher cityDataFetcher;

    private final CityService cityService;

    public ScheduledProcessor(CountryDataFetcher countryDataFetcher,
                              CountryService countryService,
                              CityDataFetcher cityDataFetcher,
                              CityService cityService) {
        this.countryDataFetcher = countryDataFetcher;
        this.countryService = countryService;
        this.cityDataFetcher = cityDataFetcher;
        this.cityService = cityService;
    }

    // Cron expression: Runs at 00:00 hours on the 1st day of the 4th, 8th, and 12th months.
    @Scheduled(cron = "0 0 0 1 4,8,12 *")
    public void fetchAndStoreCountries() {
        LOGGER.info("Executing countries fetching and storing...");
        List<CountryEntity> countries = countryDataFetcher.fetch();
        countryService.checkAndSave(countries);
        LOGGER.info("Countries fetched and stored successfully.");
    }

    // Cron expression: Runs at 00:00 hours on the 3rd day of every second month starting February.
    @Scheduled(cron = "0 0 0 3 2,4,6,8,10,12 *")
    public void fetchAndStoreCities() {
        LOGGER.info("Executing cities fetching and storing...");
        countryService.getAllCountries().forEach(country -> {
            List<CityEntity> cities = cityDataFetcher.fetch(country);
            cityService.checkAndSave(cities, country);
        });
        LOGGER.info("Cities fetched and stored successfully.");
    }
}
