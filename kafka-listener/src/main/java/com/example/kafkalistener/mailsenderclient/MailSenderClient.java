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

    public String restTemplateSendFormDataMail(String jsonMail) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("mail", jsonMail);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        String url = "http://host.docker.internal:8081/api/mail/sendmailformdata";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return ("Response Status Code: " + response.getStatusCode() + "Response Body: " + response.getBody());
    }

    public String restClientSendFormDataMail(String jsonMail) {

        URI uri = UriComponentsBuilder.fromHttpUrl("http://host.docker.internal:8081/api/mail/sendmailformdata")
                .build()
                .toUri();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("mail", jsonMail);

        ResponseEntity<String> response = restClient.post()
                .uri(uri)
                .body(body)
                .retrieve()
                .toEntity(String.class);

        return ("Response Status Code: " + response.getStatusCode() + "Response Body: " + response.getBody());
    }
}
