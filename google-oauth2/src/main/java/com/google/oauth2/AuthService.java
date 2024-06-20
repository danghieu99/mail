package com.google.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    private String client_id = clientRegistrationRepository.findByRegistrationId("mail").getClientId();
    private String client_secret = clientRegistrationRepository.findByRegistrationId("mail").getClientSecret();
    private String tokenUri = "https://oauth2.googleapis.com/token";

    private String obtainAccessTokenFromGoogle(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> tokenRequest = new HashMap<>();
        tokenRequest.put("grant_type", "password");
        tokenRequest.put("scope", "https://mail.google.com/");
        tokenRequest.put("client_id", client_id);
        tokenRequest.put("client_secret", client_secret);
        tokenRequest.put("username", username);
        tokenRequest.put("password", password);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUri, tokenRequest, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            return (String) responseBody.get("access_token");
        }

        return null;
    }

}
