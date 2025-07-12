package com.example.consumer.config;

import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Config {

    // @Bean
    // public ModelMapper modelMapper() {
    //     return new ModelMapper();
    // }

    // @Bean
    // public DefaultErrorHandler errorHandler() {
    //     DefaultErrorHandler handler = new DefaultErrorHandler(
    //         (record, exception) -> {
    //             log.error("Final failure for offset {}: {}", record.offset(), exception.getMessage());
    //         },
    //         new FixedBackOff(1000L, 2) // 2 попытки с интервалом 1 секунда
    //     );
    //     handler.addNotRetryableExceptions(IllegalStateException.class);
    //     return handler;
    // }
}
