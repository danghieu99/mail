package com.mail.kafkamailsender.dto;

import java.time.ZonedDateTime;

public class MailSchedule {

    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private String frequency;

    public MailSchedule() {
    }

    private MailSchedule(Builder builder) {
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.frequency = builder.frequency;
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

    public static MailSchedule.Builder startTime(ZonedDateTime startTime) {
        return new MailSchedule.Builder(startTime);
    }

    public static class Builder {
        private ZonedDateTime startTime;
        private ZonedDateTime endTime;
        private String frequency;

        public Builder(ZonedDateTime startTime) {
            this.startTime = startTime;
        }

        public Builder endTime(ZonedDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder frequency(String frequency) {
            this.frequency = frequency;
            return this;
        }

        public MailSchedule build() {
            return new MailSchedule(this);
        }
    }
}
