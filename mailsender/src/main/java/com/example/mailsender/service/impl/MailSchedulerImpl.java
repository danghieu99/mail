package com.example.mailsender.service.impl;

import com.example.mailsender.dto.MailData;
import com.example.mailsender.service.MailScheduler;
import org.springframework.stereotype.Service;

@Service
public class MailSchedulerImpl implements MailScheduler {

    @Override
    public String scheduleMail(MailData mailData) {
        return "";
    }
}
