package com.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.domain"})
@ConfigurationPropertiesScan(value = {"com.domain"})
public class SoundaryApiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoundaryApiServerApplication.class, args);
    }

}
