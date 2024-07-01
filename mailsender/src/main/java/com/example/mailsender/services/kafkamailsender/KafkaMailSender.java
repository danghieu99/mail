package com.example.mailsender.services.kafkamailsender;

import com.example.mailsender.common.dtos.MailData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface KafkaMailSender {

    String sendMailParams(String from, Collection<String> to, String subject, String body, Collection<MultipartFile> files,
                          Collection<String> cc, Collection<String> bcc, Collection<String> replyTo);

    String sendMail(MailData mailData);

    String sendMailJson(String mailJson);

}
