package com.example.mailsender.common.services.mimemessage;

import com.example.mailsender.common.dtos.MailData;
import jakarta.mail.internet.MimeMessage;

public interface MimeMessageService {

    MimeMessage createMimeMessage(MailData mailData);

}
