package com.example.mailsender.controller;

import com.example.mailsender.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController

@RequestMapping("/api/mail")
public class MailSenderController {

    @Autowired
    private MailSender mailSender;

    @PostMapping("/sendmailwithattachmentfile")
    public String sendMailWithAttachmentFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("sender") String sender,
                                             @RequestParam List<String> recipients,
                                             @RequestParam("subject") String subject,
                                             @RequestParam("content") String content) {
        return mailSender.sendMailWithAttachmentFile(file, sender, recipients, subject, content);
    }

    @PostMapping("/sendmailwithattachmenturl")
    public String sendMailWithAttachmentUrl(@RequestParam("file") MultipartFile file,
                                            @RequestParam("sender") String sender,
                                            @RequestParam List<String> recipients,
                                            @RequestParam("subject") String subject,
                                            @RequestParam("content") String content) {
        return mailSender.sendMailWithAttachmentFile(file, sender, recipients, subject, content);
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestParam List<String> recipients,
                           @RequestParam("sender") String sender,
                           @RequestParam("subject") String subject,
                           @RequestParam("body") String body) {
        return mailSender.sendMail(sender, recipients, subject, body);
    }

    @PostMapping("/sendmaildata")
    public String sendMailFormData(@RequestParam("mail") String mailJson) {
        return mailSender.sendMailData(mailJson);
    }
}
