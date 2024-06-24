package com.example.mailsender.service;

import jakarta.mail.Session;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public interface JavaMailSenderFactory {
    public JavaMailSenderImpl createJavaMailSender(Session session);
}
