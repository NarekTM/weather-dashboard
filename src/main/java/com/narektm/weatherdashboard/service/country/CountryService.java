package com.narektm.weatherdashboard.service.country;

import com.narektm.weatherdashboard.entity.CountryEntity;
import com.narektm.weatherdashboard.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
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
