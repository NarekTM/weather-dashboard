package com.narektm.weatherdashboard.service.country;

import com.narektm.weatherdashboard.dto.CountryDto;
import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.repository.CountryRepository;
import com.narektm.weatherdashboard.service.converter.CountryConverter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    private final CountryConverter countryConverter;

    public CountryService(CountryRepository countryRepository,
                          CountryConverter countryConverter) {
        this.countryRepository = countryRepository;
        this.countryConverter = countryConverter;
    }

    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }

    @Cacheable("activeCountries")
    public List<CountryDto> getAllActiveCountries() {
        return countryRepository.findAllByActiveIsTrue().stream()
                .map(countryConverter::toDto)
                .toList();
    }

    public void checkAndSave(List<CountryEntity> countries) {
        countries.forEach(country -> countryRepository.findByCca2(country.getCca2())
                .ifPresentOrElse(existingCountry -> checkAndSave(existingCountry, country),
                        () -> countryRepository.save(country)
                ));
    }

    private void checkAndSave(CountryEntity existingCountry, CountryEntity country) {
        if (!existingCountry.equals(country)) {
            update(existingCountry, country);
            countryRepository.save(existingCountry);
        }
    }

    private void update(CountryEntity existingCountry, CountryEntity sourceCountry) {
        existingCountry.setCcn3(sourceCountry.getCcn3());
        existingCountry.setCca3(sourceCountry.getCca3());
        existingCountry.setName(sourceCountry.getName());
        existingCountry.setCapitalCity(sourceCountry.getCapitalCity());
        existingCountry.setRegion(sourceCountry.getRegion());
        existingCountry.setSubRegion(sourceCountry.getSubRegion());
        existingCountry.setPhoneCode(sourceCountry.getPhoneCode());
        existingCountry.setFlagUri(sourceCountry.getFlagUri());
    }
}
