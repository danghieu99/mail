package com.example.mailsender.service;

public interface MailAuth {

    public String fetchAccessToken(String username, String password);

}
