package com.example.mailsender.mailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController

@RequestMapping("/api/mail")
public class MailSenderController {

    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/sendmailwithattachment")
    public String sendMailWithAttachment(@RequestParam("file") MultipartFile file,
                                         @RequestParam("sender") String sender,
                                         @RequestParam List<String> recipients,
                                         @RequestParam("subject") String subject,
                                         @RequestParam("content") String content) {
        return mailSenderService.sendMailWithAttachment(file, sender, recipients, subject, content);
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestParam List<String> recipients,
                           @RequestParam("sender") String sender,
                           @RequestParam("subject") String subject,
                           @RequestParam("body") String body) {
        return mailSenderService.sendMail(sender, recipients, subject, body);
    }

    @PostMapping("/sendmailjson")
    public String sendMail(@RequestParam("mailjson") String mailJson) {
        return mailSenderService.sendMail(mailJson);
    }
}
