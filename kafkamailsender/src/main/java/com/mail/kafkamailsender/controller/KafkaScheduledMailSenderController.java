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
@RequestMapping("/kafka/schedule")
public class KafkaScheduledMailSenderController {

    @Autowired
    private KafkaMailSender kafkaMailSender;

    @PostMapping("/mail")
    public String sendScheduledmail(@RequestParam("from") String from,
                                    @RequestParam List<String> to,
                                    @RequestParam("subject") String subject,
                                    @RequestParam("body") String body,
                                    @RequestParam(value = "file", required = false) Collection<MultipartFile> files,
                                    @RequestParam("start") String startTime,
                                    @RequestParam(value = "end", required = false) String endTime,
                                    @RequestParam(value = "frequency", required = false) String frequency) {
        return kafkaMailSender.sendScheduledMail(from, to, subject, body, files, startTime, endTime, frequency);
    }

    @PostMapping("/mailjson")
    public String sendScheduledMailJson(@RequestParam("mail") String scheduledMail) {
        return kafkaMailSender.sendMailJson(scheduledMail);
    }
}
