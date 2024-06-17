package com.example.kafkalistener.mailsenderclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Component
public class MailSenderClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestClient restClient;

    public String sendJsonMail(String jsonMail) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("mailjson", jsonMail);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "http://host.docker.internal:8081/api/mail/sendmailjson";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return ("Response Status Code: " + response.getStatusCode() + "Response Body: " + response.getBody());
    }

    /*
    public String restClientSendJsonMail(String jsonMail) {
        RestClient jsonMailClient =RestClient.builder()
                .baseUrl("http://host.docker.internal")
                .
    }

     */

}
