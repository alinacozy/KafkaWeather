package com.example.producer.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class WeatherInfo {
    City city;

    int temperature;
    
    WeatherCondition condition;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate date;
}
