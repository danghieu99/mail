package com.example.kafkalistener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class MailSenderClient {

    @Autowired
    private RestClient restClient;

    public String SendMailData(String jsonMail) {

        String host = System.getenv("MAIL_HOST");
        int port = Integer.valueOf(System.getenv("MAIL_PORT"));
        String scheme = System.getenv("MAIL_SCHEME");
        String path = System.getenv("MAIL_PATH");

        URI uri = UriComponentsBuilder.newInstance()
                .host(host)
                .scheme(scheme)
                .port(port)
                .path(path)
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
