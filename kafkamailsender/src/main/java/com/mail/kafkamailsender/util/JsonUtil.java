package com.mail.kafkamailsender.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mail.kafkamailsender.dto.MailData;

import java.time.ZonedDateTime;

public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String mailDataToJson(MailData mailData) {
        try {
            return mapper.writeValueAsString(mailData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ZonedDateTime jsonToZonedDateTime(String json) {
        try {
            return mapper.readValue(json, ZonedDateTime.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
