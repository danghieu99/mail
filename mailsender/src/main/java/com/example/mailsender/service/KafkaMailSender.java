package com.example.mailsender.service;

import com.example.mailsender.dto.MailData;
import com.example.mailsender.util.MailDataToJson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class KafkaMailSender {

    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    @Autowired
    MinioFileClient minioFileClient;

    public String sendMessage(String message) {
        String topic = "mailtest";
        String key = String.valueOf(UUID.randomUUID());
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        });
        return "message sent";
    }

    public String sendMail(String from, List<String> to, String subject, String body) {
        MailData mail = new MailData(from, to, subject, body);
        String topic = "mailtest";
        String jsonMessage = MailDataToJson.toJson(mail);
        String key = String.valueOf(UUID.randomUUID());

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonMessage);
        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        });
        return "mail sent";
    }

    public String sendMailWithAttachments(String from, List<String> to, String subject, String body, Collection<MultipartFile> files) {

        HashMap<String, String> attachments = minioFileClient.uploadAttachmentFiles(files);

        MailData mailWIthAttachmentMinioUrl = new MailData(from, to, subject, body, attachments);

        String jsonMessage = MailDataToJson.toJson(mailWIthAttachmentMinioUrl);
        String topic = "mailtest";
        String key = UUID.randomUUID().toString();

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonMessage);

        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        });
        return "mail with attachment sent";
    }
}
