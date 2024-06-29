package com.example.mailsender.dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MailData {

    private List<String> to;
    private String from;
    private String subject;
    private String body;
    private Collection<String> replyTo;
    private Collection<String> cc;
    private Collection<String> bcc;
    private HashMap<String, String> attachments;
    private MailSchedule mailSchedule;

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
        this.mailSchedule = builder.mailSchedule;
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

    public Collection<String> getReplyTo() {
        return replyTo;
    }

    public Collection<String> getCc() {
        return cc;
    }

    public Collection<String> getBcc() {
        return bcc;
    }

    public MailSchedule getMailSchedule() {
        return mailSchedule;
    }

    public static class Builder {

        private List<String> to;
        private String from;
        private String subject;
        private String body;
        private HashMap<String, String> attachments;
        private Collection<String> replyTo;
        private Collection<String> cc;
        private Collection<String> bcc;
        private MailSchedule mailSchedule;

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

        public Builder mailSchedule(MailSchedule mailSchedule) {
            this.mailSchedule = mailSchedule;
            return this;
        }

        public MailData build() {
            return new MailData(this);
        }
    }
}
