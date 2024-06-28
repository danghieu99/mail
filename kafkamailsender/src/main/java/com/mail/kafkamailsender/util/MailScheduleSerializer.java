package com.mail.kafkamailsender.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mail.kafkamailsender.dto.MailSchedule;

public class MailScheduleSerializer {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(MailSchedule mailSchedule) {
        try {
            return mapper.writeValueAsString(mailSchedule);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
