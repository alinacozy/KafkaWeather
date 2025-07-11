package com.example.producer.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class WeatherInfo {
    City city;
    int temperature;
    WeatherCondition condition;
    LocalDate date;
}
