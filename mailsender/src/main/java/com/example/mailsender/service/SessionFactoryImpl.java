package com.example.mailsender.service;

import jakarta.mail.Session;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;

public interface SessionFactoryImpl {

    public Session createSession(String token);

}
