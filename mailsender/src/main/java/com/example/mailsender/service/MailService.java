package com.example.mailsender.service;

import com.example.mailsender.dto.MailData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface MailService {

    String sendMailWithAttachmentFiles(String from, List<String> to, String subject, String content, Collection<MultipartFile> files);

    String sendMailWithAttachments(String from, List<String> to, String subject, String content, HashMap<String, String> attachments);

    String sendMail(String sender, List<String> recipients, String subject, String content);

    String sendMailJson(String mailJson);

    String scheduleMail(String scheduledMailJson);

    String sendMail(MailData mailData);
}
