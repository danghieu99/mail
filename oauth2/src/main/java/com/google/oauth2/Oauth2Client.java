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

    private String client_id = "993587433485-ikigka0qg4osb33nio2dfmjppccnagpl.apps.googleusercontent.com";
    private String client_secret = "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";
    private String tokenUri = "https://oauth2.googleapis.com/token";

    public String fetchAccessTokenFromGoogle(String username, String password) {

        System.out.println(client_id);
        System.out.println(client_secret);
        System.out.println(client_secret);

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
            String access_token = (String) responseBody.get("access_token");
            return access_token;
        }

        System.out.println("null token");
        return null;
    }
}
