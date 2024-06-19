package com.example.mailsender.service;

import com.example.mailsender.dto.MailData;
import com.example.mailsender.util.MailDataToJson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
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

    public String sendMail(String sender, List<String> recipients, String subject, String content) {
        MailData mail = new MailData(sender, recipients, subject, content);
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

    public String sendMailWithAttachmentFile(String sender, List<String> recipients, String subject, String content, MultipartFile file) {

        String fileName = UUID.randomUUID() + file.getOriginalFilename();

        try {
            minioFileClient.uploadFile(file, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

        String attachmentUrl = minioFileClient.fetchFileUrl(fileName);

        MailData mailWIthAttachmentMinioUrl = new MailData(sender, recipients, subject, content, attachmentUrl, fileName);

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
