package com.example.mailsender.service.mailauth;

public interface TokenService {

    public String requestAuthorizationCode(String username);

    public String requestAccessToken(String authorizationCode);

}
