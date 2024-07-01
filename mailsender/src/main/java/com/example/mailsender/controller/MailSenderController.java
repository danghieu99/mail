package com.example.mailsender.controller;

import com.example.mailsender.service.mailsender.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
@RequestMapping("/mail/send")
public class MailSenderController {

    private MailSenderService mailSenderService;

    @Autowired
    public MailSenderController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/mailparams")
    public String sendMailParams(@RequestParam("from") String from,
                                 @RequestParam("to") Collection<String> to,
                                 @RequestParam("subject") String subject,
                                 @RequestParam("body") String body,
                                 @RequestParam(value = "file", required = false) Collection<MultipartFile> files,
                                 @RequestParam(value = "cc", required = false) Collection<String> cc,
                                 @RequestParam(value = "bcc", required = false) Collection<String> bcc,
                                 @RequestParam(value = "replyto", required = false) Collection<String> replyTo) {
        return mailSenderService.sendMailParams(from, to, subject, body, files, cc, bcc, replyTo);
    }

    @PostMapping("/mailjson")
    public String sendMailJson(@RequestParam("mail") String mailJson) {
        return mailSenderService.sendMailJson(mailJson);
    }
}