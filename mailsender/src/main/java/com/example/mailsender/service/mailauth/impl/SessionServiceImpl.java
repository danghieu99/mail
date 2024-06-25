package com.example.mailsender.service.mailauth.impl;

import com.example.mailsender.service.mailauth.SessionService;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public Session createSession(String username, String token) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.sasl.enable", "true");
        props.put("mail.smtp.sasl.mechanisms", "XOAUTH2");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, token);
            }
        });
    }
}
