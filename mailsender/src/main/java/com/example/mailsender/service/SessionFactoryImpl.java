package com.example.mailsender.service;

import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;

public interface SessionFactoryImpl {

    public JavaMailSender createSession(HashMap<String, String> credentials);

}
