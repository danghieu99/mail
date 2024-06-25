package com.example.mailsender.service.mailauth.impl;

import com.example.mailsender.service.mailauth.MailAuth;
import com.example.mailsender.service.mailauth.SessionService;
import com.example.mailsender.service.mailauth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailAuthImpl implements MailAuth {

    private final SessionService sessionService;
    private final TokenService tokenService;

    @Autowired
    public MailAuthImpl(SessionService sessionService, TokenService tokenService) {
        this.sessionService = sessionService;
        this.tokenService = tokenService;
    }

    @Override
    public String requestAccessToken(String authorizationCode) {
        return tokenService.requestAccessToken(authorizationCode);
    }

    @Override
    public String requestAuthorizationCode(String username) {
        return tokenService.requestAuthorizationCode(username);
    }

    @Override
    public String receiveAuthorizationCode(String code) {
        return code;
    }

    @Override
    public String receiveAccessToken(String token) {
        return token;
    }
}
