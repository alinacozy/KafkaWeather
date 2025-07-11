package com.example.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.ProducerFactory;

import com.example.producer.model.WeatherInfo;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.Map;


@Configuration
public class Config {

    private final KafkaProperties kafkaProperties;

    public Config(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ProducerFactory<String, WeatherInfo> producerFactory() {
        // get configs on application.yml
        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, WeatherInfo> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name("weather")
                .partitions(1)
                .replicas(1)
                .build();
    }

}