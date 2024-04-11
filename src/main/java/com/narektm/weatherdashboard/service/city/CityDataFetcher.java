package com.narektm.weatherdashboard.service.city;

import com.narektm.weatherdashboard.entity.CityEntity;
import com.narektm.weatherdashboard.entity.CountryEntity;

import java.util.List;

public interface CityDataFetcher {

    List<CityEntity> fetch(CountryEntity country);
}
