package com.example.kafkalistener.mailsenderclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class MailSenderClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestClient restClient;

    public String restTemplateSendJsonMail(String jsonMail) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("mailjson", jsonMail);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "http://host.docker.internal:8081/api/mail/sendmailjson";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return ("Response Status Code: " + response.getStatusCode() + "Response Body: " + response.getBody());
    }


    public String restClientSendJsonMail(String jsonMail) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        URI uri = UriComponentsBuilder.fromHttpUrl("http://host.docker.internal:8081/api/mail/sendmailjson")
                .build()
                .toUri();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("mailjson", jsonMail);

        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .body(body)
                .retrieve()
                .toEntity(String.class);

        return ("Response Status Code: " + response.getStatusCode() + "Response Body: " + response.getBody());
    }
}
