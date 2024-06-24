package com.mail.kafkamailsender.controller;

import com.mail.kafkamailsender.service.KafkaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/kafka")
public class KafkaMailSenderController {

    @Autowired
    KafkaMailSender kafkaMailSender;

    @PostMapping("/sendmessage")
    public String sendMessage(@RequestParam("message") String message) {
        return kafkaMailSender.sendMessage(message);
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestParam List<String> to,
                           @RequestParam("from") String from,
                           @RequestParam("subject") String subject,
                           @RequestParam("body") String body) {
        return kafkaMailSender.sendMail(from, to, subject, body);
    }

    @PostMapping("/sendmailwithattachments")
    public String sendMailWithAttachment(@RequestParam("from") String from,
                                         @RequestParam List<String> to,
                                         @RequestParam("subject") String subject,
                                         @RequestParam("body") String body,
                                         @RequestParam("file") Collection<MultipartFile> files) {

        return kafkaMailSender.sendMailWithAttachmentFiles(from, to, subject, body, files);
    }
}
