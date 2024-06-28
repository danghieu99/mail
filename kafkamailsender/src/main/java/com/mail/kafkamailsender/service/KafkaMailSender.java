package com.mail.kafkamailsender.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

public interface KafkaMailSender {

    String sendMessage(String message);

    String sendMail(String from, List<String> to, String subject, String body);

    String sendMailWithAttachmentFiles(String from, List<String> to, String subject, String body, Collection<MultipartFile> files);

    String sendScheduledMail(String from, List<String> to, String subject, String body, Collection<MultipartFile> files, String startTime, String endTime, String frequency);

    String sendScheduledMailJson(String scheduledMail);
}
