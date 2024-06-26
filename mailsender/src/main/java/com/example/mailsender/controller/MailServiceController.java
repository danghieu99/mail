package com.example.mailsender.controller;

import com.example.mailsender.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController

@RequestMapping("/mail")
public class MailServiceController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendmailwithattachments")
    public String sendMailWithAttachments(@RequestParam("from") String from,
                                          @RequestParam List<String> to,
                                          @RequestParam("subject") String subject,
                                          @RequestParam("body") String body,
                                          @RequestParam("files") Collection<MultipartFile> files) {
        return mailService.sendMailWithAttachmentFiles(from, to, subject, body, files);
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestParam("from") String from,
                           @RequestParam List<String> to,
                           @RequestParam("subject") String subject,
                           @RequestParam("body") String body) {
        return mailService.sendMail(from, to, subject, body);
    }

    @PostMapping("/sendmailjson")
    public String sendMailJson(@RequestParam("mail") String mailJson) {
        return mailService.SendMailJson(mailJson);
    }
}
