package com.mail.kafkamailsender.service.impl;

import com.mail.kafkamailsender.service.MinioFileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;

@Service
public class MinioFileClientImpl implements MinioFileClient {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String uploadFile(MultipartFile file, String fileName) throws IOException {

        String url = System.getenv("MINIO_API_UPLOAD_ENDPOINT");
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
        body.add("bucketname", "newbucket");
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String response = restTemplate.postForObject(url, requestEntity, String.class);
        return response;
    }

    @Override
    public HashMap<String, String> uploadAttachmentFiles(Collection<MultipartFile> files) {
        List<String> fileNames = new ArrayList<>();

        HashMap<String, MultipartFile> uploadFiles = new HashMap<>();
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            fileNames.add(fileName);
            uploadFiles.put(fileName, file);
        }

        try {
            uploadFiles.forEach((fileName, file) -> {
                try {
                    uploadFile(file, fileName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, String> urlAttachments = new HashMap<>();
        for (String fileName : fileNames) {
            String attachmentUrl = fetchFileUrl(fileName);
            urlAttachments.put(attachmentUrl, fileName);
        }

        return urlAttachments;
    }

    @Override
    public String fetchFileUrl(String fileName) {

        String apiEndpoint = System.getenv("MINI0_API_GETURL_ENDPOINT");

        String url = UriComponentsBuilder.fromHttpUrl(apiEndpoint)
                .queryParam("filename", fileName)
                .queryParam("bucketname", "newbucket")
                .build()
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}
