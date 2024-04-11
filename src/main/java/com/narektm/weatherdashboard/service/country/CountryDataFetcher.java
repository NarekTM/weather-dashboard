package com.narektm.weatherdashboard.service;

import com.narektm.weatherdashboard.entity.CountryEntity;

import java.util.List;

public interface CountryDataFetcher {

    List<CountryEntity> fetchCountries();
}
