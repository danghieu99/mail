package com.example.mailsender.dto;

import java.util.List;

public class MailData {

    private List<String> recipients;
    private String sender;
    private String subject;
    private String content;
    private String attachmentUrl;
    private String fileName;

    public MailData() {
    }

    public MailData(String sender, List<String> recipients, String subject, String content, String attachmentUrl, String fileName) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.attachmentUrl = attachmentUrl;
        this.fileName = fileName;
        this.recipients = recipients;
    }

    public MailData(String sender, List<String> recipients, String subject, String content) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.recipients = recipients;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public String getFileName() {return fileName;}
}
