package com.narektm.weatherdashboard.service.country;

import com.narektm.weatherdashboard.entity.CountryEntity;

import java.util.List;

public interface CountryDataFetcher {

    List<CountryEntity> fetch();
}
