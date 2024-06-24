package com.example.mailsender.service;

public interface MailAuth {

    public String requestAccessToken(String username, String password);

    public String receiveAccessToken(String token);
}
