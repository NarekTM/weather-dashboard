package com.narektm.weatherdashboard.service.converter;

import com.narektm.weatherdashboard.dto.CountryDto;
import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.entity.CountryNameEmbeddable;
import org.springframework.stereotype.Component;

@Component
public class CountryConverter {

    public CountryDto toDto(CountryEntity country) {
        CountryNameEmbeddable countryName = country.getName();

        return new CountryDto(country.getId(),
                countryName.getCommon(),
                countryName.getCommonNative());
    }
}
