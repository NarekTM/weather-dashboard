package com.narektm.weatherdashboard.repository;

import com.narektm.weatherdashboard.entity.CityEntity;
import com.narektm.weatherdashboard.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByCountryAndName(CountryEntity country, String name);

    List<CityEntity> findAllByCountryIdAndActiveIsTrue(Long countryId);
}
