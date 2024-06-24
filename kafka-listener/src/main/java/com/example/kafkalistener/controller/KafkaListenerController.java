package com.example.kafkalistener.controller;

import com.example.kafkalistener.service.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kafkalistener")
public class KafkaListenerController {

    @Autowired
    private KafkaListener kafkaListener;

    @PostMapping
    public String subscribe(List<String> topics) {
        return kafkaListener.subscribe(topics);
    }

}
