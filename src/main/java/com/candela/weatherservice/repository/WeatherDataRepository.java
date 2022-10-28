package com.candela.weatherservice.repository;

import com.candela.weatherservice.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Integer> {

    List<WeatherData> findByWeatherDate(LocalDateTime localDateTime);
}
