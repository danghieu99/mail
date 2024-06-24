package com.example.mailsender.service.impl;

import com.example.mailsender.service.SessionManager;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManagerImpl implements SessionManager {

    private Map<String, Session> sessions = new ConcurrentHashMap<>();

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

    @Override
    public String addSession(String username, String token) {
        Session session = createSession(username, token);
        sessions.put(username, session);
        return username;
    }

    @Override
    public Session retrieveSession(String username) {
        return sessions.get(username);
    }
}
