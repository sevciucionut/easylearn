package com.learning.easylearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EntityScan("com.learning.easylearn.entity")
@EnableConfigurationProperties
public class EasylearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasylearnApplication.class, args);
    }

}
