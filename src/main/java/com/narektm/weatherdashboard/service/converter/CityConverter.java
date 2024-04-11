package com.narektm.weatherdashboard.service.converter;

import com.narektm.weatherdashboard.dto.CityDto;
import com.narektm.weatherdashboard.entity.CityEntity;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {

    public CityDto toDto(CityEntity city) {
        return new CityDto(city.getId(),
                city.getName());
    }
}
