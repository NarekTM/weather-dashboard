package com.narektm.weatherdashboard.repository;

import com.narektm.weatherdashboard.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    Optional<CountryEntity> findByCca2(String cca2);

    List<CountryEntity> findAllByActiveIsTrue();
}
