package com.example.mailsender.service.impl;

import com.example.mailsender.dto.MailData;
import com.example.mailsender.service.MailService;
import com.example.mailsender.service.MinioFileClient;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.example.mailsender.util.MailDataSerializer.toMailData;

@Service
public class MailServiceImpl implements MailService {

    private final MinioFileClient minioFileClient;
    private final JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(MinioFileClient minioFileClient, JavaMailSender mailSender) {
        this.minioFileClient = minioFileClient;
        this.mailSender = mailSender;
    }

    @Override
    public String sendMailWithAttachmentFiles(String from, List<String> to, String subject, String content,
                                              Collection<MultipartFile> files) {

        if (files.isEmpty()) {
            throw new RuntimeException("files not found");
        }

        HashMap<String, String> attachments = minioFileClient.uploadAttachmentFiles(files);

        return sendMailWithAttachments(from, to, subject, content, attachments);
    }

    @Override
    public String sendMailWithAttachments(String from, List<String> to,
                                          String subject, String body, HashMap<String, String> attachments) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to.toArray(new String[to.size()]));
            helper.setText(body);
            helper.setSubject(subject);

            attachments.forEach((url, filename) -> {
                try {
                    InputStream fileStream = new URL(url).openStream();
                    byte[] attachmentBytes = fileStream.readAllBytes();
                    ByteArrayResource attachmentSource = new ByteArrayResource(attachmentBytes);
                    helper.addAttachment(filename, attachmentSource);

                } catch (IOException | MessagingException e) {
                    throw new RuntimeException(e);
                }
            });

            mailSender.send(message);

        } catch (MessagingException e) {
            return e.getMessage();
        }
        return "mail with attachment sent successfully";
    }

    @Override
    public String sendMail(String from, List<String> to, String subject, String body) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to.toArray(new String[to.size()]));
            message.setText(body);
            message.setSubject(subject);
            message.setFrom(from);

            mailSender.send(message);

            return "mail sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    @Override
    public String sendMailJson(String mailJson) {

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
