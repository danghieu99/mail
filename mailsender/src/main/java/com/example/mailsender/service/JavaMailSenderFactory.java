package com.example.mailsender.service;

import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;

public interface JavaMailSenderFactory {

    public JavaMailSender createMailSender(HashMap<String, String> credentials);

}
