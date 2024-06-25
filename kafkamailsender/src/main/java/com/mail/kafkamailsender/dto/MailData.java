package com.mail.kafkamailsender.dto;

import java.util.HashMap;
import java.util.List;

public class MailData {

    private List<String> to;
    private String from;
    private String subject;
    private String body;
    private String replyTo;
    private String cc;
    private String bcc;
    private HashMap<String, String> attachments;

    public MailData() {
    }

    private MailData(Builder builder) {
        this.to = builder.to;
        this.from = builder.from;
        this.subject = builder.subject;
        this.body = builder.body;
        this.attachments = builder.attachments;
        this.replyTo = builder.replyTo;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
    }

    public static Builder from(String from) {
        return new Builder(from);
    }

    public List<String> getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public HashMap<String, String> getAttachments() {
        return attachments;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getCc() {
        return cc;
    }

    public String getBcc() {
        return bcc;
    }

    public static class Builder {

        private List<String> to;
        private String from;
        private String subject;
        private String body;
        private HashMap<String, String> attachments;
        private String replyTo;
        private String cc;
        private String bcc;

        private Builder(String from) {
            this.from = from;
        }

        public Builder to(List<String> to) {
            this.to = to;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder attachments(HashMap<String, String> attachments) {
            this.attachments = attachments;
            return this;
        }

        public Builder replyTo(String replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        public Builder cc(String cc) {
            this.cc = cc;
            return this;
        }

        public Builder bcc(String bcc) {
            this.bcc = bcc;
            return this;
        }

        public MailData build() {
            return new MailData(this);
        }
    }
}