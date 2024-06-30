package com.example.mailsender.controller;

import com.example.mailsender.service.MailDataService;
import com.example.mailsender.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController

@RequestMapping("/mail/send")
public class MailSenderController {

    private MailSenderService mailSenderService;
    private MailDataService mailDataService;

    @Autowired
    public MailSenderController(MailSenderService mailSenderService, MailDataService mailDataService) {
        this.mailSenderService = mailSenderService;
        this.mailDataService = mailDataService;
    }

    @PostMapping("/mailparams")
    public String sendMailParams(@RequestParam("from") String from,
                                 @RequestParam("to") List<String> to,
                                 @RequestParam("subject") String subject,
                                 @RequestParam("body") String body,
                                 @RequestParam(value = "file", required = false) Collection<MultipartFile> files,
                                 @RequestParam(value = "cc", required = false) List<String> cc,
                                 @RequestParam(value = "bcc", required = false) List<String> bcc,
                                 @RequestParam(value = "replyto", required = false) List<String> replyTo) {
        return mailSenderService.sendMail(mailDataService.createMailData(from, to, subject, body, files, cc, bcc, replyTo));
    }

    @PostMapping("/mailjson")
    public String sendMailJson(@RequestParam("mail") String mailJson) {
        return mailSenderService.sendMailJson(mailJson);
    }
}