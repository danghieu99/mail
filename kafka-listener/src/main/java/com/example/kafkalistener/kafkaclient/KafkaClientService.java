package com.example.kafkalistener.kafkaclient;

import com.example.kafkalistener.mailsenderclient.MailSenderClientService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class KafkaClientService {

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;

    @Autowired
    private MailSenderClientService mailSenderClientService;

    public void listen() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                    String mailJson = record.value();
                    mailSenderClientService.sendJsonMail(mailJson);
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
