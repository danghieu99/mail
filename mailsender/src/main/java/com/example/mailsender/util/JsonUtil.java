package com.example.mailsender.util;

import com.example.mailsender.dto.MailData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.ZonedDateTime;

public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static MailData toMailData(String json) {
        try {
            return mapper.readValue(json, MailData.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ZonedDateTime toZonedDateTime(String json) {
        try {
            return mapper.readValue(json, ZonedDateTime.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
