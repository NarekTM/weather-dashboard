package com.narektm.weatherdashboard.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledProcessor {

    private final GeoService geoService;

    public ScheduledProcessor(GeoService geoService) {
        this.geoService = geoService;
    }

    // Cron expression: Runs at 00:00 hours on the 1st day of the 4th, 8th, and 12th months.
    @Scheduled(cron = "0 0 0 1 4,8,12 *")
    public void fetchAndStoreCountries() {
        geoService.fetchAndStoreCountries();
    }

    // Cron expression: Runs at 00:00 hours on the 3rd day of every second month starting February.
    @Scheduled(cron = "0 0 0 3 2,4,6,8,10,12 *")
    public void fetchAndStoreCities() {
        geoService.fetchAndStoreCities();
    }
}
