package com.example.kafkalistener.services.kafkalistener;

import com.example.kafkalistener.services.mailsenderclient.MailSenderClientImpl;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaListenerImpl implements KafkaListener {

    private final KafkaConsumer<String, String> kafkaConsumer;
    private final MailSenderClientImpl mailSenderClient;

    @Autowired
    public KafkaListenerImpl(KafkaConsumer<String, String> kafkaConsumer,
                             MailSenderClientImpl mailSenderClient) {
        this.kafkaConsumer = kafkaConsumer;
        this.mailSenderClient = mailSenderClient;
    }

    public void run() throws InterruptedException {
        List<String> topics = new ArrayList<>();
        topics.add("mailtest");
        topics.add("scheduledmail");
        subscribe(topics);
        listen();
    }

    public void listen() {
        while (true) {
            try {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                    String mailRecord = record.value();
                    mailSenderClient.SendMailData(mailRecord);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String subscribe(List<String> topics) {
        try {
            kafkaConsumer.subscribe(topics);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Subscribed to " + topics.toString();
    }
}
