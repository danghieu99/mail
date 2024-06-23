package com.example.mailsender.controller;

import com.example.mailsender.service.GoogleMailAuth;
import com.example.mailsender.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController

@RequestMapping("/api/mail")
public class MailSenderController {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private GoogleMailAuth googleMailAuth;

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String username, @RequestParam String password) {
        return googleMailAuth.requestAccessToken(username, password);
    }

    @PostMapping("/token")
    private void receiveAccessToken(@RequestParam String token) {
        System.out.println(token);
    }

    @PostMapping("/sendmailwithattachments")
    public String sendMailWithAttachments(@RequestParam("from") String from,
                                          @RequestParam List<String> to,
                                          @RequestParam("subject") String subject,
                                          @RequestParam("body") String body,
                                          @RequestParam("files") Collection<MultipartFile> files) {
        return mailSender.sendMailWithAttachmentFiles(from, to, subject, body, files);
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestParam("from") String from,
                           @RequestParam List<String> to,
                           @RequestParam("subject") String subject,
                           @RequestParam("body") String body) {
        return mailSender.sendMail(from, to, subject, body);
    }

    @PostMapping("/sendmailjson")
    public String sendMailFormData(@RequestParam("mail") String mailJson) {
        return mailSender.SendMailJson(mailJson);
    }
}
