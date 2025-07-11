package com.example.producer.producer;

import java.time.LocalDate;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.producer.generator.WeatherGenerator;
import com.example.producer.model.City;
import com.example.producer.model.WeatherInfo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class WeatherProducer {
    private final KafkaTemplate<String, WeatherInfo> kafkaTemplate;
    private final WeatherGenerator generator;

    private LocalDate currentDate = LocalDate.now();

    @Scheduled(fixedRate = 10 * 1000) // раз в 10 секунд
    public void sendRandomWeather() {
        for (City city : City.values()) { // генерация и отправка погоды во всех городах
            WeatherInfo weather = generator.generateRandomWeather(currentDate, city);
            kafkaTemplate.send("weather", city.name(), weather);

        }
        currentDate=currentDate.plusDays(1);
    }
}
