package com.example.mailsender.service.mimemessage;

import com.example.mailsender.dto.MailData;
import jakarta.mail.internet.MimeMessage;

public interface MimeMessageService {

    MimeMessage createMimeMessage(MailData mailData);

}
