package com.example.consumer.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.consumer.model.City;
import com.example.consumer.model.WeatherCondition;
import com.example.consumer.model.WeatherInfo;
import com.example.consumer.model.WeatherStatsDTO;

@Service
public class WeatherStatsService {

    private final List<WeatherInfo> allWeatherData = new ArrayList<>();

    // обновляет данные при получении нового сообщения
    public void addWeatherData(WeatherInfo weather) {
        allWeatherData.add(weather);
    }

    public WeatherStatsDTO getStats() {
        WeatherStatsDTO stats = new WeatherStatsDTO();

        //получение последнего дня статистики
        LocalDate today = allWeatherData.stream()
                .map(WeatherInfo::getDate)
                .max(LocalDate::compareTo)
                .orElse(LocalDate.now());
        stats.setToday(today);

        // группировка данных по городам
        Map<City, List<WeatherInfo>> dataByCity = allWeatherData.stream()
                .collect(Collectors.groupingBy(WeatherInfo::getCity));

        // средняя температура по городам
        Map<City, Double> avgTempMap = new HashMap<>();
        dataByCity.forEach((city, weatherList) -> {
            double avg = weatherList.stream()
                    .mapToInt(WeatherInfo::getTemperature)
                    .average()
                    .orElse(0.0);
            avgTempMap.put(city, avg);
        });
        stats.setAvgTempByCity(avgTempMap);

        // самое частое состояние по городам
        Map<City, String> commonConditionMap = new HashMap<>();
        dataByCity.forEach((city, weatherList) -> {
            Map<WeatherCondition, Long> conditionCounts = weatherList.stream()
                    .collect(Collectors.groupingBy(
                            WeatherInfo::getCondition,
                            Collectors.counting()));

            String mostCommon = conditionCounts.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(entry -> entry.getKey().name())
                    .orElse("N/A");

            commonConditionMap.put(city, mostCommon);
        });
        stats.setMostCommonCondition(commonConditionMap);

        //самый жаркий день
        WeatherInfo hottest = allWeatherData.stream()
                .max(Comparator.comparingInt(WeatherInfo::getTemperature))
                .orElseThrow();
        stats.setHottestDay(hottest.getDate());
        stats.setHottestDayCity(hottest.getCity().name());
        stats.setHottestTemp(hottest.getTemperature());

        //самый холодный день
        WeatherInfo coldest = allWeatherData.stream()
                .min(Comparator.comparingInt(WeatherInfo::getTemperature))
                .orElseThrow();
        stats.setColdestDay(coldest.getDate());
        stats.setColdestDayCity(coldest.getCity().name());
        stats.setColdestTemp(coldest.getTemperature());

        return stats;
    }

}
