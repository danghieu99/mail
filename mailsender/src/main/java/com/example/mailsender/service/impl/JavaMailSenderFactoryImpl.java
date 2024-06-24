package com.example.mailsender.service.impl;

import com.example.mailsender.service.JavaMailSenderFactory;
import jakarta.mail.Session;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class JavaMailSenderFactoryImpl implements JavaMailSenderFactory {

    @Override
    public JavaMailSenderImpl createJavaMailSender(Session session) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setSession(session);

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.sasl.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth.mechanisms", "XOAUTH2");
        return javaMailSender;
    }

}
