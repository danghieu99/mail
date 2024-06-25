package com.example.mailsender.service.mailauth.impl;

import com.example.mailsender.service.mailauth.MailAuth;
import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Properties;

@Service
public class MailAuthImpl implements MailAuth {

    private final RestClient restClient;

    @Autowired
    public MailAuthImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public String fetchGoogleAuthenticationUrl() {
        URI uri = UriComponentsBuilder.newInstance()
                .host("host.docker.internal")
                .scheme("http")
                .port(8084)
                .path("/oauth2/auth")
                .build()
                .toUri();

        ResponseEntity<String> response = restClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(String.class);

        return response.getBody();
    }

    @Override
    public String fetchAccessToken(String authorizationCode) {

        URI uri = UriComponentsBuilder.newInstance()
                .host("host.docker.internal")
                .scheme("http")
                .port(8084)
                .path("/oauth2/token")
                .queryParam("authorization_code", authorizationCode)
                .build()
                .toUri();

        ResponseEntity<String> response = restClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(String.class);
        return response.getBody();
    }

    @Override
    public Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.sasl.enable", "true");
        props.put("mail.smtp.sasl.mechanisms", "XOAUTH2");

        return null;
    }
}
