package com.mail.kafkamailsender.service.impl;

import com.mail.kafkamailsender.dto.MailData;
import com.mail.kafkamailsender.dto.MailSchedule;
import com.mail.kafkamailsender.service.KafkaMailSender;
import com.mail.kafkamailsender.util.JsonUtil;
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
        String jsonMessage = JsonUtil.mailDataToJson(mail);
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

        if (files == null) {
            return sendMail(from, to, subject, body);
        }

        if (files.isEmpty()) {
            return sendMail(from, to, subject, body);
        }

        HashMap<String, String> attachments = minioFileClient.uploadAttachmentFiles(files);

        MailData mailWithAttachmentsData = MailData.from(from).to(to).subject(subject).body(body).attachments(attachments).build();

        String jsonMessage = JsonUtil.mailDataToJson(mailWithAttachmentsData);
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
    public String sendScheduledMail(String from, List<String> to, String subject, String body, Collection<MultipartFile> files,
                                    String startTime, String endTime, String frequency) {

        ZonedDateTime start = JsonUtil.jsonToZonedDateTime(startTime);
        ZonedDateTime end = JsonUtil.jsonToZonedDateTime(endTime);
        MailSchedule schedule = MailSchedule.startTime(start).endTime(end).frequency(frequency).build();

        MailData scheduledMail;
        if (files == null) {
            scheduledMail = MailData.from(from).to(to).subject(subject).body(body).mailSchedule(schedule).build();
        } else if (files.isEmpty()) {
            scheduledMail = MailData.from(from).to(to).subject(subject).body(body).mailSchedule(schedule).build();
        } else {
            HashMap<String, String> attachments = minioFileClient.uploadAttachmentFiles(files);
            scheduledMail = MailData.from(from).to(to).subject(subject).body(body).attachments(attachments).mailSchedule(schedule).build();
        }

        String scheduledMailJson = JsonUtil.mailDataToJson(scheduledMail);

        return sendMailJson(scheduledMailJson);
    }

    @Override
    public String sendMailJson(String scheduledMail) {
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
}