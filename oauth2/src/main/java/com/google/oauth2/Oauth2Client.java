package com.google.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class Oauth2Client {

    @Autowired
    private RestClient restClient;

    private String client_id = System.getenv("CLIENT_ID");
    private String client_secret = System.getenv("CLIENT_SECRET");
    private String tokenUri = System.getenv("TOKEN_URI");

    public String fetchAccessTokenFromGoogle(String username, String password) {

        Map<String, String> tokenRequest = new HashMap<>();
        tokenRequest.put("grant_type", "password");
        tokenRequest.put("client_id", client_id);
        tokenRequest.put("client_secret", client_secret);
        tokenRequest.put("username", username);
        tokenRequest.put("password", password);
        tokenRequest.put("scope", "https://www.googleapis.com/auth/gmail.send");

        ResponseEntity<Map> response = restClient.post()
                .uri(tokenUri)
                .body(tokenRequest)
                .retrieve()
                .toEntity(Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            return String.valueOf(responseBody.get("access_token"));
        }

        System.out.println("null token");
        return null;
    }
}
