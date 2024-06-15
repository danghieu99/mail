package com.example.mailsender.mailsender;

import com.example.mailsender.client.MinioFileClient;
import com.example.mailsender.dto.MailData;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import static com.example.mailsender.util.JsonToMailData.toMailData;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    MinioFileClient minioFileClient;

    public String sendMailWithAttachmentFile(MultipartFile file, String sender, List<String> recipients,
                                         String subject, String content) {

        String fileName = UUID.randomUUID() + file.getOriginalFilename();

        try {
            minioFileClient.uploadFile(file, fileName);
        } catch (Exception e) {
            return e.getMessage();
        }

        String fileUrl = minioFileClient.fetchFileUrl(fileName);

        try (InputStream fileStream = new URL(fileUrl).openStream()) {

            byte[] attachmentBytes = fileStream.readAllBytes();

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            for (String recipient : recipients) {
                helper.addTo(recipient);
            }
            helper.setText(content);
            helper.setSubject(subject);

            ByteArrayResource attachmentSource = new ByteArrayResource(attachmentBytes);
            helper.addAttachment(fileName, attachmentSource);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "mail with attachment sent successfully";
    }

    private String sendMailWithAttachmentUrl(String attachmentUrl, String sender, List<String> recipients,
                                          String subject, String content) {

        String fileName = UUID.randomUUID() + "attachment";

        try (InputStream fileStream = new URL(attachmentUrl).openStream()) {

            byte[] attachmentBytes = fileStream.readAllBytes();

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(recipients.toArray(new String[recipients.size()]));
            helper.setText(content);
            helper.setSubject(subject);

            ByteArrayResource attachmentSource = new ByteArrayResource(attachmentBytes);
            helper.addAttachment(fileName, attachmentSource);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
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

    public String sendMailJson(String mailJson) {

        try {
            MailData mailData = toMailData(mailJson);
            List<String> recipients = mailData.getRecipients();
            String sender = mailData.getSender();
            String subject = mailData.getSubject();
            String content = mailData.getContent();

            if (mailData.getAttachmentUrl() == null) {
                System.out.println("Sending mail with no attachment!");
                return sendMail(sender, recipients, subject, content);
            } else {
                String attachmentUrl = minioFileClient.fetchFileUrl(mailData.getAttachmentUrl());
                System.out.println("Sending mail with attachment url!");
                return sendMailWithAttachmentUrl(attachmentUrl, sender, recipients, subject, content);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
