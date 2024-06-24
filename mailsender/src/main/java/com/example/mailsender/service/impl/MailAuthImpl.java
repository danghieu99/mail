package com.example.mailsender.service.impl;

import com.example.mailsender.service.MailAuth;
import com.example.mailsender.service.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MailAuthImpl implements MailAuth {

    @Autowired
    RestClient restClient;

    @Autowired
    SessionManager sessionManager;

    @Override
    public String requestAccessToken(String username, String password) {

        URI uri = UriComponentsBuilder.newInstance()
                .host("host.docker.internal")
                .scheme("http")
                .port(8084)
                .path("/api/oauth2/authenticate")
                .build()
                .toUri();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        body.add("password", password);

        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .body(body)
                .retrieve()
                .toEntity(String.class);

        String token = response.getBody();

        if (token != null) {
            sessionManager.addSession(username, token);
            return (username + ":" + password);
        }
        return "null token";
    }


    //add completablefuture
    @Override
    public String receiveAccessToken(String token) {
        System.out.println("token received " + token);
        return token;
    }
}
