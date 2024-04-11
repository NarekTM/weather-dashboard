package com.narektm.weatherdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherDashboardApplication.class, args);
    }

}
