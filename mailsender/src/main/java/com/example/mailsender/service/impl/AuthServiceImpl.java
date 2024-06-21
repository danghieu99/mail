package com.example.mailsender.service.impl;

import com.example.mailsender.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    RestClient restClient;

    @Override
    public String requestAccessToken(String username, String password) {
        return "";

    }
}
