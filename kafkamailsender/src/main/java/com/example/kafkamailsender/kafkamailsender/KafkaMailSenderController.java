package com.example.kafkamailsender.kafkamailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/kafka")
public class KafkaMailSenderController {

    @Autowired
    KafkaMailSenderService kafkaMailSenderService;

    @PostMapping("/sendmessage")
    public String sendMessage(@RequestParam("message") String message) {
        return kafkaMailSenderService.sendMessage(message);
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestParam List<String> recipients,
                           @RequestParam("sender") String sender,
                           @RequestParam("subject") String subject,
                           @RequestParam("content") String content) {
        return kafkaMailSenderService.sendMail(sender, recipients, subject, content);
    }

    @PostMapping("/sendmailwithattachment")
    public String sendMailWithAttachment(@RequestParam("sender") String sender,
                                         @RequestParam List<String> recipients,
                                         @RequestParam("subject") String subject,
                                         @RequestParam("content") String content,
                                         @RequestParam("file") MultipartFile file) {

        return kafkaMailSenderService.sendMailWithAttachmentFile(sender, recipients, subject, content, file);
    }
}
