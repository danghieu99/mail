package com.example.mailsender.service;

import com.example.mailsender.dto.MailData;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface MailService {

    public String sendMailWithAttachmentFiles(String from, List<String> to, String subject, String content, Collection<MultipartFile> files);

    public String sendMailWithAttachments(String from, List<String> to, String subject, String content, HashMap<String, String> attachments);

    public String sendMail(String sender, List<String> recipients, String subject, String content);

    public String sendMailJson(String mailJson);

}
