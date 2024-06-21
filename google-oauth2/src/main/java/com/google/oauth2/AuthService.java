package com.google.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private RestClient restClient;

    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;

    ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("mail");
    private String client_id = clientRegistration.getClientId();
    private String client_secret = clientRegistration.getClientSecret();
    private String tokenUri = "https://oauth2.googleapis.com/token";

    public String fetchAccessTokenFromGoogle(HashMap<String, String> credentials) {

//        Map<String, String> tokenRequest = new HashMap<>();
//        tokenRequest.put("grant_type", "password");
//        tokenRequest.put("client_id", client_id);
//        tokenRequest.put("client_secret", client_secret);
//        tokenRequest.put("username", credentials.keySet().iterator().next());
//        tokenRequest.put("password", credentials.values().iterator().next());
//        tokenRequest.put("scope", "https://mail.google.com/");
//
//        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUri, tokenRequest, Map.class);

        Map<String, String> tokenRequest = new HashMap<>();
        tokenRequest.put("grant_type", "password");
        tokenRequest.put("client_id", client_id);
        tokenRequest.put("client_secret", client_secret);
        tokenRequest.put("username", credentials.keySet().iterator().next());
        tokenRequest.put("password", credentials.values().iterator().next());
        tokenRequest.put("scope", "https://mail.google.com/");

        ResponseEntity<Map> response = restClient.post()
                .uri(tokenUri)
                .body(tokenRequest)
                .retrieve()
                .toEntity(Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            return (String) responseBody.get("access_token");
        }

        return null;
    }

}
