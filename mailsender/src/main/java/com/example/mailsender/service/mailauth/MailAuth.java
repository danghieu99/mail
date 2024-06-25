package com.example.mailsender.service.mailauth;

import jakarta.mail.Session;

public interface MailAuth {

    public String fetchAccessToken(String authorizationCode);

    public String fetchGoogleAuthenticationUrl();

    public Session createSession();

}
