package com.example.kafkalistener.service.mailsenderclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MailSenderClientImpl implements MailSenderClient {

    private final RestClient restClient;

    @Autowired
    public MailSenderClientImpl(RestClient restClient) {
        this.restClient = restClient;
    }


    public String SendMailData(String jsonMail) {

        URI uri = UriComponentsBuilder.newInstance()
                .host("host.docker.internal")
                .scheme("http")
                .port(8081)
                .path("/mail/send/mailjson")
                .build()
                .toUri();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("mail", jsonMail);

        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .accept(MediaType.MULTIPART_FORM_DATA)
                .body(body)
                .retrieve()
                .toEntity(String.class);

        return ("Response Status Code: " + response.getStatusCode() + "Response Body: " + response.getBody());
    }
}
