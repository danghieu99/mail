package com.example.mailsender.controller;

import com.example.mailsender.service.MailDataService;
import com.example.mailsender.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/kafka/send")
public class KafkaMailSenderController {


    private MailSenderService mailSenderService;
    private MailDataService mailDataService;

    @Autowired
    public KafkaMailSenderController(MailSenderService mailSenderService, MailDataService mailDataService) {
        this.mailSenderService = mailSenderService;
        this.mailDataService = mailDataService;
    }

    @PostMapping("/mail")
    public String sendMailParams(@RequestParam("from") String from,
                                 @RequestParam List<String> to,
                                 @RequestParam("subject") String subject,
                                 @RequestParam("body") String body,
                                 @RequestParam(value = "file", required = false) Collection<MultipartFile> files,
                                 @RequestParam(value = "replyto", required = false) Collection<String> replyTo,
                                 @RequestParam(value = "cc", required = false) Collection<String> cc,
                                 @RequestParam(value = "bcc", required = false) Collection<String> bcc) {

        return mailSenderService.sendMail(mailDataService.createMailData(from, to, subject, body, files, replyTo, cc, bcc));
    }

    @PostMapping("/mailjson")
    public String sendMailJson(@RequestParam("mailjson") String mailJson) {
        return mailSenderService.sendMailJson(mailJson);
    }
}
