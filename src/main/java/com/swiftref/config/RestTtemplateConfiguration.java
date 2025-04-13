package com.swiftref.config;

import java.time.Duration;

import com.swiftref.exceptionHandler.ApiExceptionHandler;
import com.swiftref.exceptionHandler.ExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTtemplateConfiguration {

    @Value("${backend.timeout}")
    private int timeout;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .readTimeout(Duration.ofSeconds(timeout))
                .errorHandler(new ExceptionHandler()).build();
    }

}
