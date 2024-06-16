package com.example.mailsender.miniofileclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class MinioFileClient {

    @Autowired
    private RestTemplate restTemplate;

    public String uploadFile(MultipartFile file, String fileName) throws IOException {

        String url = "http://host.docker.internal:8080/api/file/upload";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {

            @Override
            public String getFilename() {
                return fileName;
            }
        };

        body.add("file", resource);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String response = restTemplate.postForObject(url, requestEntity, String.class);
        return response;
    }

    public String fetchFileUrl(String fileName) {
        String url = UriComponentsBuilder.fromHttpUrl("http://host.docker.internal:8080/api/file/geturl")
                .queryParam("filename", fileName)
                .queryParam("bucketname", "newbucket")
                .build()
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}
