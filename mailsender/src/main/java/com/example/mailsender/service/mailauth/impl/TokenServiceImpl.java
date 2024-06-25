package com.example.mailsender.service.mailauth.impl;

import com.example.mailsender.service.mailauth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@Service
public class TokenServiceImpl implements TokenService {

    private final RestClient restClient;

    @Autowired
    public TokenServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public String requestAuthorizationCode(String username) {
        URI uri = UriComponentsBuilder.newInstance()
                .host("host.docker.internal")
                .scheme("http")
                .port(8084)
                .path("/oauth2/auth")
                .build()
                .toUri();

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("username", username);

        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .body(requestParams)
                .retrieve()
                .toEntity(String.class);

        return response.getBody();
    }

    @Override
    public String requestAccessToken(String authorizationCode) {

        URI uri = UriComponentsBuilder.newInstance()
                .host("host.docker.internal")
                .scheme("http")
                .port(8084)
                .path("/oauth2/token")
                .build()
                .toUri();

        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put("authorization_code", authorizationCode);

        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .body(requestParams)
                .retrieve()
                .toEntity(String.class);

        return response.getBody();
    }
}
