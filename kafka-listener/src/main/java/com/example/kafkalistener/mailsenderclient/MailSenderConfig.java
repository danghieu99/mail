package com.example.kafkalistener.mailsenderclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MailSenderConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}