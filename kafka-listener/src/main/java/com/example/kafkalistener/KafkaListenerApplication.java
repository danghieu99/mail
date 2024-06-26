package com.example.kafkalistener;

import com.example.kafkalistener.services.kafkalistener.KafkaListenerImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaListenerApplication.class, args);
    }

    @Autowired
    KafkaListenerImpl kafkaListener;

    @PostConstruct
    public void init() {
            try {
                kafkaListener.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
