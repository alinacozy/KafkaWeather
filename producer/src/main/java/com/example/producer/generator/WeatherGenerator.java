package com.example.producer.generator;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.producer.model.City;
import com.example.producer.model.WeatherCondition;
import com.example.producer.model.WeatherInfo;

@Component
public class WeatherGenerator {

    private static final WeatherCondition[] CONDITIONS = WeatherCondition.values();

    public WeatherInfo generateRandomWeather(LocalDate date, City city) {
        Random random = new Random();
        return new WeatherInfo(
                city,
                random.nextInt(50) - 10, // температура от -40 до +40
                CONDITIONS[random.nextInt(CONDITIONS.length)], // случайное состояние погоды
                date
        );
    }
}
