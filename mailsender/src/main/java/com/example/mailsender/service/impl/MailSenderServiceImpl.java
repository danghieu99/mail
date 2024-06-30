package com.example.mailsender.service.impl;

import com.example.mailsender.dto.MailData;
import com.example.mailsender.dto.MailSchedule;
import com.example.mailsender.service.MailScheduler;
import com.example.mailsender.service.MailSenderService;
import com.example.mailsender.util.JsonUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public MimeMessage createMessage(MailData mailData) {
        Collection<String> to = mailData.getTo();
        String from = mailData.getFrom();
        String subject = mailData.getSubject();
        String body = mailData.getBody();
        Collection<String> cc = mailData.getCc();
        Collection<String> bcc = mailData.getBcc();
        Collection<String> replyTo = mailData.getReplyTo();
        HashMap<String, String> attachments = mailData.getAttachments();

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to.toArray(new String[to.size()]));
            helper.setText(body);
            helper.setSubject(subject);
            if (replyTo != null) {
                helper.setReplyTo(Arrays.toString(replyTo.toArray(new String[replyTo.size()])));
            }
            if (cc != null) {
                helper.setCc(Arrays.toString(cc.toArray(new String[cc.size()])));
            }
            if (bcc != null) {
                helper.setBcc(Arrays.toString(bcc.toArray(new String[bcc.size()])));
            }
            if (attachments == null) {
                return message;
            } else if (attachments.isEmpty()) {
                return message;
            } else {
                attachments.forEach((url, filename) -> {
                    try {
                        InputStream fileStream = new URL(url).openStream();
                        byte[] attachmentBytes = fileStream.readAllBytes();
                        ByteArrayResource attachmentSource = new ByteArrayResource(attachmentBytes);
                        helper.addAttachment(filename, attachmentSource);

                    } catch (IOException | MessagingException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            return message;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String sendMailJson(String mailJson) {
        MailData mailData = JsonUtil.jsonToMailData(mailJson);
        return sendMail(mailData);
    }

    @Override
    public String sendMail(MailData mailData) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "mail sent";
    }


}
