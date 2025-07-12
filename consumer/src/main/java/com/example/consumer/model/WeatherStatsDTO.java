package com.example.consumer.model;

import java.time.LocalDate;
import java.util.Map;

import lombok.Data;

@Data
public class WeatherStatsDTO {
    private LocalDate today;
    private Map<City, Double> avgTempByCity;
    private Map<City, String> mostCommonCondition;

    private String hottestDayCity;
    private LocalDate hottestDay; 
    private int hottestTemp;

    private String coldestDayCity;          
    private LocalDate coldestDay;
    private int coldestTemp;
    
}
