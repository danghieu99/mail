package com.example.mailsender.service.impl;

import com.example.mailsender.service.JavaMailSenderFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Properties;

@Service
public class JavaMailSenderFactoryImpl implements JavaMailSenderFactory {

    @Override
    public JavaMailSender createMailSender(HashMap<String, String> credentials) {

        String username = credentials.keySet().iterator().next();
        String password = credentials.get(username);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
