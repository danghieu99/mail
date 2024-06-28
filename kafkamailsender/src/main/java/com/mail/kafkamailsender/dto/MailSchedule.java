package com.mail.kafkamailsender.dto;

import java.time.ZonedDateTime;

public class MailSchedule {

    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private String frequency;

    public MailSchedule() {
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public String getFrequency() {
        return frequency;
    }

}
