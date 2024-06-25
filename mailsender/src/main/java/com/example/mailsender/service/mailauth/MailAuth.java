package com.example.mailsender.service.mailauth;

import jakarta.mail.Session;

public interface MailAuth {

    public String requestAccessToken(String authorizationCode);

    public String requestAuthorizationCode(String username);

    public String receiveAuthorizationCode(String code);

    public String receiveAccessToken(String token);

}
