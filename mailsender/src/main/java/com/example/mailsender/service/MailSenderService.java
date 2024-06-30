package com.example.mailsender.service;

import com.example.mailsender.dto.MailData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface MailSenderService {

    String sendMailJson(String mailJson);

    String sendMail(MailData mailData);
}
