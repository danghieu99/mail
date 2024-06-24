package com.example.kafkalistener.service;

import org.springframework.stereotype.Service;

@Service
public interface MailSenderClient {

    public String SendMailData(String jsonMail);

}
