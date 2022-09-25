package com.project.weather.repositories;

import com.project.weather.entities.WeatherEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    boolean existsByCity(String city);
    WeatherEntity findByCity(String city);

    List<WeatherEntity> findByOrderByCount();

}
