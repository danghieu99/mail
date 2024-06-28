package com.mail.kafkamailsender.service.impl;

import com.mail.kafkamailsender.dto.MailData;
import com.mail.kafkamailsender.dto.MailSchedule;
import com.mail.kafkamailsender.service.KafkaMailSender;
import com.mail.kafkamailsender.util.MailDataSerializer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class KafkaMailSenderImpl implements KafkaMailSender {

    @Autowired
    private KafkaProducer<String, String> kafkaProducer;

    @Autowired
    MinioFileClientImpl minioFileClient;

    @Override
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

    @Override
    public String sendMail(String from, List<String> to, String subject, String body) {

        MailData mail = MailData.from(from).to(to).subject(subject).body(body).build();

        String topic = "mailtest";
        String jsonMessage = MailDataSerializer.mailToJson(mail);
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

    @Override
    public String sendMailWithAttachmentFiles(String from, List<String> to, String subject, String body, Collection<MultipartFile> files) {

        if (files.isEmpty()) {
            throw new RuntimeException("no files found");
        }

        HashMap<String, String> attachments = minioFileClient.uploadAttachmentFiles(files);

        MailData mailWithAttachmentsData = MailData.from(from).to(to).subject(subject).body(body).attachments(attachments).build();

        String jsonMessage = MailDataSerializer.mailToJson(mailWithAttachmentsData);
        String topic = "mailtest";
        String key = UUID.randomUUID().toString();

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonMessage);

        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        });
        return "mail with attachments sent";
    }

    @Override
    public String sendScheduledMailJson(String scheduledMail) {
        String topic = "scheduledmail";
        String key = UUID.randomUUID().toString();

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, scheduledMail);

        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
                throw new RuntimeException(exception);
            }
        });

        return "scheduled mail sent";
    }

    @Override
    public String sendScheduledMail(String from, List<String> to, String subject, String body, Collection<MultipartFile> files,
                                    ZonedDateTime startTime, ZonedDateTime endTime, String frequency) {

        MailSchedule schedule = MailSchedule.startTime(startTime).endTime(endTime).frequency(frequency).build();

        MailData scheduledMail;

        if (files.isEmpty()) {
            scheduledMail = MailData.from(from).to(to).subject(subject).body(body).mailSchedule(schedule).build();
        } else {
            HashMap<String, String> attachments = minioFileClient.uploadAttachmentFiles(files);
            scheduledMail = MailData.from(from).to(to).subject(subject).body(body).attachments(attachments).mailSchedule(schedule).build();
        }

        String scheduledMailJson = MailDataSerializer.mailToJson(scheduledMail);

        return sendScheduledMailJson(scheduledMailJson);
    }
}
