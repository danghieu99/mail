package com.example.mailsender.controllers;

import com.example.mailsender.services.kafkamailsender.KafkaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
@RequestMapping("/kafka/send")
public class KafkaMailSenderController {

    private KafkaMailSender kafkaMailSender;

    @Autowired
    public KafkaMailSenderController(KafkaMailSender kafkaMailSender) {
        this.kafkaMailSender = kafkaMailSender;
    }

    @PostMapping("/mailparams")
    public String sendMailParams(@RequestParam("from") String from,
                                 @RequestParam Collection<String> to,
                                 @RequestParam("subject") String subject,
                                 @RequestParam("body") String body,
                                 @RequestParam(value = "file", required = false) Collection<MultipartFile> files,
                                 @RequestParam(value = "cc", required = false) Collection<String> cc,
                                 @RequestParam(value = "bcc", required = false) Collection<String> bcc,
                                 @RequestParam(value = "replyto", required = false) Collection<String> replyTo) {
        return kafkaMailSender.sendMailParams(from, to, subject, body, files, replyTo, cc, bcc);
    }

    @PostMapping("/mailjson")
    public String sendMailJson(@RequestParam("mailjson") String mailJson) {
        return kafkaMailSender.sendMailJson(mailJson);
    }
}
