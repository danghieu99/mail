package com.example.mailsender.service.impl;

import com.example.mailsender.dto.MailData;
import com.example.mailsender.dto.MailSchedule;
import com.example.mailsender.service.MailScheduler;
import com.example.mailsender.service.MailService;
import com.example.mailsender.service.MinioFileClient;
import com.example.mailsender.util.JsonUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static com.example.mailsender.util.JsonUtil.jsonToMailData;

@Service
public class MailServiceImpl implements MailService {

    private final MinioFileClient minioFileClient;
    private final JavaMailSender mailSender;
    private final MailScheduler mailScheduler;

    @Autowired
    public MailServiceImpl(MinioFileClient minioFileClient, JavaMailSender mailSender, MailScheduler mailScheduler) {
        this.minioFileClient = minioFileClient;
        this.mailSender = mailSender;
        this.mailScheduler = mailScheduler;
    }

    @Override
    public String sendMailWithAttachmentFiles(String from, List<String> to, String subject, String body, Collection<MultipartFile> files, List<String> cc, List<String> bcc, List<String> replyTo) {
        HashMap<String, String> attachments = minioFileClient.uploadAttachmentFiles(files);
        MailData mailData = MailData.from(from).to(to).subject(subject).body(body).attachments(attachments).cc(cc).bcc(bcc).replyTo(replyTo).build();
        return sendMail(mailData);
    }

    @Override
    public String sendMailJson(String mailJson) {
        MailData mailData = jsonToMailData(mailJson);
        return sendMail(mailData);
    }

    @Override
    public String sendMail(MailData mailData) {
        List<String> to = mailData.getTo();
        String from = mailData.getFrom();
        String subject = mailData.getSubject();
        String body = mailData.getBody();
        Collection<String> cc = mailData.getCc();
        Collection<String> bcc = mailData.getBcc();
        Collection<String> replyTo = mailData.getReplyTo();
        MailSchedule mailSchedule = mailData.getMailSchedule();
        HashMap<String, String> attachments = mailData.getAttachments();

        if ((mailSchedule) != null) {
            mailScheduler.scheduleMail(mailData);
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to.toArray(new String[to.size()]));
            helper.setText(body);
            helper.setSubject(subject);
            helper.setReplyTo(Arrays.toString(replyTo.toArray(new String[replyTo.size()])));
            helper.setCc(Arrays.toString(cc.toArray(new String[cc.size()])));
            helper.setBcc(Arrays.toString(bcc.toArray(new String[bcc.size()])));

            if (attachments == null) {
                mailSender.send(message);
            }
            if (attachments.isEmpty()) {
                mailSender.send(message);
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

            mailSender.send(message);


        } catch (MessagingException e) {
            return e.getMessage();
        }
        return "mail with attachment sent successfully";
    }
}
