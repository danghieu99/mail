package com.example.mailsender.service.impl;

import com.example.mailsender.dto.MailData;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static com.example.mailsender.util.JsonToMailData.toMailData;

@Service
public class MailSender implements com.example.mailsender.service.MailSender {

    @Autowired
    private org.springframework.mail.javamail.JavaMailSender mailSender;

    @Autowired
    MinioFileClientImpl minioFileClient;

    public String sendMailWithAttachmentFiles(String from, List<String> to, String subject, String content, Collection<MultipartFile> files) {

        if (files.isEmpty()) {
            throw new RuntimeException("files is empty");
        }

        HashMap<String, String> attachments = minioFileClient.uploadAttachmentFiles(files);

        return sendMailWithAttachments(from, to, subject, content, attachments);
    }

    public String sendMailWithAttachments(String from, List<String> to,
                                          String subject, String content, HashMap<String, String> attachments) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to.toArray(new String[to.size()]));
            helper.setText(content);
            helper.setSubject(subject);

            attachments.forEach((url, filename) -> {
                try {
                    InputStream fileStream = new URL(url).openStream();
                    byte[] attachmentBytes = fileStream.readAllBytes();
                    ByteArrayResource attachmentSource = new ByteArrayResource(attachmentBytes);
                    helper.addAttachment(filename, attachmentSource);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            mailSender.send(message);

        } catch (MessagingException e) {
            return e.getMessage();
        }
        return "mail with attachment sent successfully";
    }

    public String sendMail(String sender, List<String> recipients, String subject, String content) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(recipients.toArray(new String[recipients.size()]));
            message.setText(content);
            message.setSubject(subject);
            message.setFrom(sender);

            mailSender.send(message);

            return "mail sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    public String SendMailJson(String mailJson) {

        try {
            MailData mailData = toMailData(mailJson);
            List<String> to = mailData.getTo();
            String from = mailData.getFrom();
            String subject = mailData.getSubject();
            String body = mailData.getBody();

            if (mailData.getAttachments() == null) {
                System.out.println("Sending mail with no attachment!");
                return sendMail(from, to, subject, body);
            } else {
                HashMap<String, String> attachments = mailData.getAttachments();
                System.out.println("Sending mail with attachments!");
                return sendMailWithAttachments(from, to, subject, body, attachments);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
