package com.google.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@Service
public class Oauth2Client {

    @Autowired
    private RestClient restClient;

    private String client_id = System.getenv("CLIENT_ID");
    private String client_secret = System.getenv("CLIENT_SECRET");
    private String tokenUri = System.getenv("TOKEN_URI");
    private String authorizeUri = System.getenv("AUTHORIZE_URI");
    private String authorizationCodeEndpoint = System.getenv("AUTHORIZATION_CODE_ENDPOINT");

    public String getAuthorizationCodeFromGoogle() {

        URI uri = UriComponentsBuilder.fromHttpUrl(authorizeUri)
                .queryParam("client_id", System.getenv("CLIENT_ID"))
                .queryParam("grant_type", "authorization_code")
                .queryParam("scope", "https://mail.google.com/")
                .queryParam("response_type", "code")
                .queryParam("redirect_uri", authorizationCodeEndpoint)
                .build()
                .toUri();

        System.out.println(uri.toString());

        return uri.toString();
    }

    public String getAccessTokenFromGoogle(String authorizationCode) {

        URI uri = UriComponentsBuilder.fromHttpUrl(tokenUri)
                .build()
                .toUri();

        HashMap<String, String> tokenRequestBody = new HashMap<>();
        tokenRequestBody.put("client_id", client_id);
        tokenRequestBody.put("client_secret", client_secret);
        tokenRequestBody.put("code", authorizationCode);
        tokenRequestBody.put("grant_type", "authorization_code");

        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .body(tokenRequestBody)
                .retrieve()
                .toEntity(String.class);

        return response.getBody();
    }
}
