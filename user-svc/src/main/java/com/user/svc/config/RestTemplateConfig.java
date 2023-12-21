package com.user.svc.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
Creates and register a bean in the Spring container that helps to communicate
the microservices
*/
@Configuration
public class RestTemplateConfig {

    @Bean
    // For the Load Balancer usage
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
