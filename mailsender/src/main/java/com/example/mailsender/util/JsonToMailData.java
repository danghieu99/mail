package com.example.mailsender.util;


import com.example.mailsender.dto.MailData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonToMailData {

    private static ObjectMapper mapper = new ObjectMapper();

    public static MailData toMailData(String json) {
        try {
            return mapper.readValue(json, MailData.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}