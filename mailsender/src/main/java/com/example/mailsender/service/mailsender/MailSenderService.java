package com.example.mailsender.service.mailsender;

import com.example.mailsender.dto.MailData;
import jakarta.mail.internet.MimeMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface MailSenderService {

    String sendMailParams(String from, Collection<String> to, String subject, String body, Collection<MultipartFile> files, Collection<String> cc, Collection<String> bcc, Collection<String> replyTo);

    String sendMailJson(String mailJson);

    String sendMail(MailData mailData);

}
