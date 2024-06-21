package com.example.mailsender.controller;

import com.example.mailsender.service.AuthService;
import com.example.mailsender.service.impl.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController

@RequestMapping("/api/mail")
public class MailSenderController {

    @Autowired
    private MailSender javaMailSender;

    @Autowired
    private AuthService authService;

    @PostMapping("/authenticate")
    public String authenticateMailUser(@RequestParam String username, @RequestParam String password) {
        return authService.requestAccessToken(username, password);
    }

    @PostMapping("/awaittoken")
    private void awaitToken(@RequestParam String token) {

    }

    @PostMapping("/sendmailwithattachments")
    public String sendMailWithAttachments(@RequestParam("from") String from,
                                          @RequestParam List<String> to,
                                          @RequestParam("subject") String subject,
                                          @RequestParam("body") String body,
                                          @RequestParam("files") Collection<MultipartFile> files) {
        return javaMailSender.sendMailWithAttachmentFiles(from, to, subject, body, files);
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestParam("from") String from,
                           @RequestParam List<String> to,
                           @RequestParam("subject") String subject,
                           @RequestParam("body") String body) {
        return javaMailSender.sendMail(from, to, subject, body);
    }

    @PostMapping("/sendmailjson")
    public String sendMailFormData(@RequestParam("mail") String mailJson) {
        return javaMailSender.SendMailJson(mailJson);
    }
}
