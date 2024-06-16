package com.example.kafkalistener;

import com.example.kafkalistener.kafkalistener.KafkaListener;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class KafkaListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaListenerApplication.class, args);
    }

    @Autowired
    KafkaListener kafkaListener;

    @PostConstruct
    public void init() {

        List<String> topics = new ArrayList<>();
        topics.add("mailtest");
        kafkaListener.subscribe(topics);

        while (true) {
            kafkaListener.listen();
        }
    }
}
