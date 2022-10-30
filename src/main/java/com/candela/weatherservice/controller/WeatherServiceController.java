package com.candela.weatherservice.controller;

import com.candela.weatherservice.model.WeatherData;
import com.candela.weatherservice.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class WeatherServiceController {

    @Autowired
    WeatherDataService weatherDataService;

    @GetMapping("/weather")
    public ResponseEntity<Object> getAllWeathers
            (@RequestParam(name = "date", required = false) String date){

        if( date != null && !date.isEmpty()){
            //uso el objeto servicio, llamo al metodo que lo convierte
            return weatherDataService.getWeatherDataByDate(date);
        }

        return new ResponseEntity<>(weatherDataService.getAllWeatherData(), HttpStatus.OK);
    }
    @GetMapping("/weather/{id}")
    public ResponseEntity<Object> getWeatherDataDto(@PathVariable("id") int id){
         return weatherDataService.getWeatherDataDtoById(id);
    }


    @PostMapping("/weather")
    public ResponseEntity<Object> saveWeatherData(@RequestBody WeatherData weatherData) {
        return weatherDataService.saveWeatherData(weatherData);
    }


}
