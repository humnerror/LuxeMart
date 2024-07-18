package com.luxemart.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfiguration {


    @Bean
    public RestTemplate getNewTemplate(){
        return new RestTemplate();
    }




}
