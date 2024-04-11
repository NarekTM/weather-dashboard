package com.narektm.weatherdashboard.service;

import com.narektm.weatherdashboard.dto.CountryDto;
import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.repository.CountryRepository;
import com.narektm.weatherdashboard.service.converter.CountryConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryConverter countryConverter;

    public CountryService(CountryRepository countryRepository,
                          CountryConverter countryConverter) {
        this.countryRepository = countryRepository;
        this.countryConverter = countryConverter;
    }

    public List<CountryDto> getAllCountries() {
        return countryRepository.findAllByActiveIsTrue().stream()
                .map(countryConverter::toDto)
                .collect(Collectors.toList());
    }

    public void checkAndSave(List<CountryEntity> countries) {
        countries.forEach(country -> {
            Optional<CountryEntity> existingCountryOpt = countryRepository.findByCca2(country.getCca2());

            if (existingCountryOpt.isPresent()) {
                CountryEntity existingCountry = existingCountryOpt.get();
                if (!existingCountry.equals(country)) {
                    update(existingCountry, country);
                    countryRepository.save(existingCountry);
                }
            } else {
                countryRepository.save(country);
            }
        });
    }

    private void update(CountryEntity existingCountry, CountryEntity newCountry) {
        existingCountry.setCcn3(newCountry.getCcn3());
        existingCountry.setCca3(newCountry.getCca3());
        existingCountry.setName(newCountry.getName());
        existingCountry.setCapitalCity(newCountry.getCapitalCity());
        existingCountry.setRegion(newCountry.getRegion());
        existingCountry.setSubRegion(newCountry.getSubRegion());
        existingCountry.setPhoneCode(newCountry.getPhoneCode());
        existingCountry.setFlagUri(newCountry.getFlagUri());
    }
}
