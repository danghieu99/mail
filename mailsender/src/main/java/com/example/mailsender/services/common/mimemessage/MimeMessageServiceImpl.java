package com.example.mailsender.services.common.mimemessage;

import com.example.mailsender.dtos.MailData;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;

@Service
public class MimeMessageServiceImpl implements MimeMessageService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MimeMessageServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public MimeMessage createMimeMessage(MailData mailData) {
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
                if (!replyTo.isEmpty()) {
                    message.setReplyTo(replyTo.stream().map(s -> {
                        try {
                            return new InternetAddress(s);
                        } catch (AddressException e) {
                            throw new RuntimeException(e);
                        }
                    }).toArray(InternetAddress[]::new));
                }
            }
            if (cc != null) {
                if (!cc.isEmpty()) {
                    helper.setCc(cc.toArray(new String[cc.size()]));
                }
            }
            if (bcc != null) {
                if (!bcc.isEmpty()) {
                    helper.setBcc(bcc.toArray(new String[bcc.size()]));
                }
            }
            if (attachments != null) {
                if (!attachments.isEmpty()) {
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
            }
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
