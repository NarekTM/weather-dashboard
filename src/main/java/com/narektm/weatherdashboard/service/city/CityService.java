package com.narektm.weatherdashboard.service;

import com.narektm.weatherdashboard.dto.CityDto;
import com.narektm.weatherdashboard.repository.CityRepository;
import com.narektm.weatherdashboard.service.converter.CityConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityConverter cityConverter;

    public CityService(CityRepository cityRepository,
                       CityConverter cityConverter) {
        this.cityRepository = cityRepository;
        this.cityConverter = cityConverter;
    }

    public List<CityDto> getCitiesByCountryId(Long countryId) {
        return cityRepository.findAllByCountryIdAndIsActiveIsTrue(countryId).stream()
                .map(cityConverter::toDto)
                .collect(Collectors.toList());
    }
}
