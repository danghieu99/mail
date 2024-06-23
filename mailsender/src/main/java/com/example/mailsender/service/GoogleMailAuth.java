package com.example.mailsender.service;

public interface GoogleMailAuth {

    public String requestAccessToken(String username, String password);
}
