package com.example.mailsender.service.impl;

import com.example.mailsender.service.SessionFactoryImpl;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Properties;

@Service
public class SessionFactoryImplImpl implements SessionFactoryImpl {

//    public Session createSession(String token) {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.sasl.enable", "true");
//        props.put("mail.smtp.sasl.mechanisms", "XOAUTH2");
//
//        return Session.getInstance(props, new Authenticator(){
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("your-email@gmail.com", token);
//            }
//        };
//    }
}