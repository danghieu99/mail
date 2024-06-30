package com.example.mailsender.service.mailsender;

import com.example.mailsender.dto.MailData;
import com.example.mailsender.service.maildata.MailDataService;
import com.example.mailsender.service.mimemessage.MimeMessageService;
import com.example.mailsender.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final MailDataService mailDataService;
    private final JavaMailSender javaMailSender;
    private final MimeMessageService mimeMessageService;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender, MailDataService mailDataService, MimeMessageService mimeMessageService) {
        this.javaMailSender = javaMailSender;
        this.mailDataService = mailDataService;
        this.mimeMessageService = mimeMessageService;
    }

    @Override
    public String sendMailParams(String from, Collection<String> to, String subject, String body, Collection<MultipartFile> files,
                                 Collection<String> cc, Collection<String> bcc, Collection<String> replyTo) {
        return sendMail(mailDataService.createMailData(from, to, subject, body, files, cc, bcc, replyTo));
    }

    @Override
    public String sendMailJson(String mailJson) {
        return sendMail(JsonUtil.jsonToMailData(mailJson));
    }

    @Override
    public String sendMail(MailData mailData) {
        try {
            javaMailSender.send(mimeMessageService.createMimeMessage(mailData));
        } catch (Exception e) {
            return e.getMessage();
        }
        return "mail sent";
    }
}
