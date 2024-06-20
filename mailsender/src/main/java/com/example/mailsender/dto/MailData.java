package com.example.mailsender.dto;

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

    public MailData(String from, List<String> to, String subject, String body, HashMap<String, String> attachments) {
        this.from = from;
        this.subject = subject;
        this.body = body;
        this.attachments = attachments;
        this.to = to;
    }

    public MailData(String from, List<String> to, String subject, String body) {
        this.from = from;
        this.subject = subject;
        this.body = body;
        this.to = to;
    }

    public MailData(String from, List<String> to, String subject, String body, String replyTo, String cc, String bcc) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.replyTo = replyTo;
        this.cc = cc;
        this.bcc = bcc;
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

    public String getCc() {
        return cc;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getBcc() {
        return bcc;
    }
}
