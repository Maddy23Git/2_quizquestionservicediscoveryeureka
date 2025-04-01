package com.maddy.question.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuestionConfig {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
