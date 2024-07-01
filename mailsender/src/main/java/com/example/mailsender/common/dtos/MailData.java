package com.example.mailsender.common.dtos;

import java.util.Collection;
import java.util.HashMap;

public class MailData {

    private Collection<String> to;
    private String from;
    private String subject;
    private String body;
    private Collection<String> replyTo;
    private Collection<String> cc;
    private Collection<String> bcc;
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

    public Collection<String> getTo() {
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

    public Collection<String> getReplyTo() {
        return replyTo;
    }

    public Collection<String> getCc() {
        return cc;
    }

    public Collection<String> getBcc() {
        return bcc;
    }

    public static class Builder {

        private Collection<String> to;
        private String from;
        private String subject;
        private String body;
        private HashMap<String, String> attachments;
        private Collection<String> replyTo;
        private Collection<String> cc;
        private Collection<String> bcc;

        private Builder(String from) {
            this.from = from;
        }

        public Builder to(Collection<String> to) {
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

        public Builder replyTo(Collection<String> replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        public Builder cc(Collection<String> cc) {
            this.cc = cc;
            return this;
        }

        public Builder bcc(Collection<String> bcc) {
            this.bcc = bcc;
            return this;
        }

        public MailData build() {
            return new MailData(this);
        }
    }
}
