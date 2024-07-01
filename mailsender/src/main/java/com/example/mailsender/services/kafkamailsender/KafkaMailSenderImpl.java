package com.example.mailsender.services.kafkamailsender;

import com.example.mailsender.dtos.MailData;
import com.example.mailsender.services.common.maildata.MailDataService;
import com.example.mailsender.utils.JsonUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.UUID;

@Service
public class KafkaMailSenderImpl implements KafkaMailSender {

    private final KafkaProducer<String, String> kafkaProducer;
    private final MailDataService mailDataService;

    @Autowired
    public KafkaMailSenderImpl(KafkaProducer kafkaProducer, MailDataService mailDataService) {
        this.mailDataService = mailDataService;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public String sendMailParams(String from, Collection<String> to, String subject, String body, Collection<MultipartFile> files,
                                 Collection<String> cc, Collection<String> bcc, Collection<String> replyTo) {
        return sendMailJson(JsonUtil.mailDataToJson(mailDataService.createMailData(from, to, subject, body, files, cc, bcc, replyTo)));
    }

    @Override
    public String sendMail(MailData mailData) {
        String mailJson = JsonUtil.mailDataToJson(mailData);
        return sendMailJson(mailJson);
    }

    @Override
    public String sendMailJson(String mailJson) {
        String topic = "mailtest";
        String key = UUID.randomUUID().toString();

        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, mailJson);

            kafkaProducer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                    throw new RuntimeException(exception);
                }
            });
        } catch (Exception e) {
            return e.getMessage();
        }
        return mailJson;
    }
}
