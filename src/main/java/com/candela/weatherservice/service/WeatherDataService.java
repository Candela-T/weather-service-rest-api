package com.candela.weatherservice.service;

import com.candela.weatherservice.dto.WeatherDataDTO;
import com.candela.weatherservice.model.WeatherData;
import com.candela.weatherservice.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.candela.weatherservice.mapper.WeatherDataMapper.mapWeatherDataToDTO;

@Service
public class WeatherDataService {

    @Autowired
    WeatherDataRepository weatherDataRepository;

    public List<WeatherDataDTO> getAllWeatherData(){

       return mapWeatherDataInWeatherDataDTO(weatherDataRepository.findAll());
    }

    public ResponseEntity<Object> getWeatherDataDtoById(int id) {

        Optional<WeatherData> optionalWeatherData = weatherDataRepository.findById(id);

        if (optionalWeatherData.isPresent())
            return new ResponseEntity<>(mapWeatherDataToDTO(optionalWeatherData.get()), HttpStatus.OK);
        else
            return new ResponseEntity<>("Weather Data not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getWeatherDataByDate(String date){
        LocalDateTime localDateTime;

        try {

            localDateTime = convertToLocaldateTime(date);

        } catch (DateTimeParseException e) {

            return new ResponseEntity<>("Invalid format.", HttpStatus.BAD_REQUEST);
        }
        List<WeatherData> listWeatherData = weatherDataRepository.findByWeatherDate(localDateTime);

        return new ResponseEntity<>(mapWeatherDataInWeatherDataDTO(listWeatherData), HttpStatus.OK);

    }


    private List<WeatherDataDTO> mapWeatherDataInWeatherDataDTO(List<WeatherData> listWeatherData){
        List<WeatherDataDTO> dtos = new ArrayList<>();

        listWeatherData.forEach(weatherData -> dtos.add(mapWeatherDataToDTO(weatherData)));
        return dtos;
    }

    private LocalDateTime convertToLocaldateTime(String date) throws DateTimeParseException{

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            return dateTime;
    }
}
