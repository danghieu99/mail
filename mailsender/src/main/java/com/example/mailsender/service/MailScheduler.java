package com.example.mailsender.service;

import com.example.mailsender.dto.MailData;

public interface MailScheduler {

    String scheduleMail(MailData mailData);
}
