package com.mail.kafkamailsender.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mail.kafkamailsender.dto.MailData;

public class MailDataSerializer {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(MailData mailData) {
        try {
            return mapper.writeValueAsString(mailData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
