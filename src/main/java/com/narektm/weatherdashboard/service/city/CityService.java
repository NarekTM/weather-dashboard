package com.narektm.weatherdashboard.service.city;

import com.narektm.weatherdashboard.entity.CityEntity;
import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void checkAndSave(List<CityEntity> cities, CountryEntity country) {
        cities.forEach(city -> cityRepository.findByCountryAndName(country, city.getName())
                .ifPresentOrElse(existingCity -> checkAndSave(existingCity, city),
                        () -> save(city, country)
                ));
    }

    private void checkAndSave(CityEntity existingCity, CityEntity city) {
        if (!existingCity.equals(city)) {
            update(existingCity, city);
            cityRepository.save(existingCity);
        }
    }

    private void save(CityEntity city, CountryEntity country) {
        city.setCountry(country);
        cityRepository.save(city);
    }

    private void update(CityEntity existingCity, CityEntity sourceCity) {
        existingCity.setGeoId(sourceCity.getGeoId());
        existingCity.setStateOrRegion(sourceCity.getStateOrRegion());
        existingCity.setLatitude(sourceCity.getLatitude());
        existingCity.setLongitude(sourceCity.getLongitude());
    }
}
