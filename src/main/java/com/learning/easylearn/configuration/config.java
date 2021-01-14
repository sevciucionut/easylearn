package com.learning.easylearn.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public DozerBeanMapper dozerBean() {
        return new DozerBeanMapper();
    }
}
