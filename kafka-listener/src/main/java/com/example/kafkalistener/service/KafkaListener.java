package com.example.kafkalistener.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class KafkaListener {

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;

    @Autowired
    private MailSenderClient mailSenderClient;

    public void listen() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                    String mailRecord = record.value();
                    mailSenderClient.SendMailData(mailRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String subscribe(List<String> topics) {
        try {
            kafkaConsumer.subscribe(topics);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Subscribed";
    }
}
