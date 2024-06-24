package com.example.mailsender.service.impl;

import com.example.mailsender.service.JavaMailSenderFactory;
import jakarta.mail.Session;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class JavaMailSenderFactoryImpl implements JavaMailSenderFactory {

    @Override
    public JavaMailSenderImpl createJavaMailSender(Session session) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setSession(session);
        return javaMailSender;
    }

}
