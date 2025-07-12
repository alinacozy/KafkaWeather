package com.example.consumer.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.consumer.model.WeatherInfo;
import com.example.consumer.service.WeatherStatsService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WeatherConsumer {

    private static final String weatherTopic = "weather";
    private static final String weatherGroup = "weather-group";

    @Autowired
    private WeatherStatsService statsService;

    @KafkaListener(topics = weatherTopic, groupId = weatherGroup)
    public void consume(WeatherInfo weatherInfo) {
        try {
            log.info("Received weather for {} at {}: {}°C, {}",
                    weatherInfo.getCity(),
                    weatherInfo.getDate(),
                    weatherInfo.getTemperature(),
                    weatherInfo.getCondition());
            statsService.addWeatherData(weatherInfo);

        } catch (Exception e) {
            log.error("Processing error: {}", e.getMessage());
            throw e;
        }
    }

}
