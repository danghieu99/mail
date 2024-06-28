package com.mail.kafkamailsender.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface KafkaMailSender {

    public String sendMessage(String message);

    public String sendMail(String from, List<String> to, String subject, String body);

    public String sendMailWithAttachmentFiles(String from, List<String> to, String subject, String body, Collection<MultipartFile> files);

    public String sendScheduledMail(String mail, String schedule);
}
