package com.candela.weatherservice.mapper;

import com.candela.weatherservice.dto.WeatherDataDTO;
import com.candela.weatherservice.model.WeatherData;
import com.candela.weatherservice.model.WeatherDataTemperature;

import java.util.List;
import java.util.stream.Collectors;

public class WeatherDataMapper {

    public static WeatherDataDTO mapWeatherDataToDTO(WeatherData weatherData) {

        List<Double> temperatures = weatherData.getWeatherDataTemperature()
                        .stream()
                        .map(WeatherDataTemperature::getTemperature)
                        .collect(Collectors.toList());

        return new WeatherDataDTO(weatherData.getLocation(), temperatures, weatherData.getWeatherDate());
    }
}
