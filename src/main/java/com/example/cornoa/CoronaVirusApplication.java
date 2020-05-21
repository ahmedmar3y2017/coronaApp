package com.example.cornoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class CoronaVirusApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronaVirusApplication.class, args);
    }

    @Bean
    public RestTemplate RestTemplate() {

        return new RestTemplate();

    }

}
