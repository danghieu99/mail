package com.example.mailsender.controller;

import com.example.mailsender.service.PersonalMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController

@RequestMapping("/api/mail/personal")
public class PersonalMailSenderController {

    @Autowired
    private PersonalMailSender personalMailSender;

    @RequestMapping

    @PostMapping("/sendmailwithattachments")
    public String sendMailWithAttachments(@RequestParam("from") String from,
                                          @RequestParam List<String> to,
                                          @RequestParam("subject") String subject,
                                          @RequestParam("body") String body,
                                          @RequestParam("files") Collection<MultipartFile> files) {
        return personalMailSender.sendMailWithAttachmentFiles(from, to, subject, body, files);
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestParam("from") String from,
                           @RequestParam List<String> to,
                           @RequestParam("subject") String subject,
                           @RequestParam("body") String body) {
        return personalMailSender.sendMail(from, to, subject, body);
    }

    @PostMapping("/sendmailjson")
    public String sendMailFormData(@RequestParam("mail") String mailJson) {
        return personalMailSender.SendMailJson(mailJson);
    }
}
