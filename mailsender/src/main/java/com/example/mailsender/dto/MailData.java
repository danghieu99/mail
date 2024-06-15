package com.example.mailsender.dto;

import java.util.ArrayList;
import java.util.List;

public class MailData {

    private List<String> recipients;
    private String sender;
    private String subject;
    private String content;
    private String attachmentUrl;

    public MailData() {
    }

    public MailData(String sender, List<String> recipients, String subject, String content, String attachmentUrl) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.attachmentUrl = attachmentUrl;
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
}
