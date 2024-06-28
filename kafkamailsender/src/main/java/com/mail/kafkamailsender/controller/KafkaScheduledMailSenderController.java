package com.mail.kafkamailsender.controller;

import com.mail.kafkamailsender.service.KafkaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/schedule")
public class KafkaScheduledMailSenderController {

    @Autowired
    private KafkaMailSender kafkaMailSender;

    @PostMapping("/mail")
    public String sendMail(@RequestParam("mail") String mail, @RequestParam("schedule") String schedule) {
        return kafkaMailSender.sendScheduledMail(mail, schedule);
    }
}
