package com.narektm.weatherdashboard.service.city;

import com.narektm.weatherdashboard.entity.CityEntity;
import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void checkAndSave(List<CityEntity> cities, CountryEntity country) {
        cities.forEach(city -> {
            Optional<CityEntity> existingCityOpt = cityRepository.findByCountryAndName(country, city.getName());

            if (existingCityOpt.isPresent()) {
                CityEntity existingCity = existingCityOpt.get();
                if (!existingCity.equals(city)) {
                    update(existingCity, city);
                    cityRepository.save(existingCity);
                }
            } else {
                city.setCountry(country);
                cityRepository.save(city);
            }
        });
    }

    private void update(CityEntity existingCity, CityEntity sourceCity) {
        existingCity.setGeoId(sourceCity.getGeoId());
        existingCity.setStateOrRegion(sourceCity.getStateOrRegion());
        existingCity.setLatitude(sourceCity.getLatitude());
        existingCity.setLongitude(sourceCity.getLongitude());
    }
}
