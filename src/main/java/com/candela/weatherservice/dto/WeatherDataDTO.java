package com.candela.weatherservice.dto;

import com.candela.weatherservice.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class WeatherDataDTO implements Serializable{

    private static final long serialVersionUID = -8374505684871476533L;

    @JsonProperty("location")
    public Location location;

    @JsonProperty("temperatures")
    public List<Double> temperatures;

    @JsonProperty("date")
    public LocalDateTime date;
}
