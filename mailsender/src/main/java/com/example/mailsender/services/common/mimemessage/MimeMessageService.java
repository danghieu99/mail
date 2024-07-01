package com.example.mailsender.services.common.mimemessage;

import com.example.mailsender.dtos.MailData;
import jakarta.mail.internet.MimeMessage;

public interface MimeMessageService {

    MimeMessage createMimeMessage(MailData mailData);

}
