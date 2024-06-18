package com.minio.api.service;

import io.minio.MinioClient;
import org.springframework.stereotype.Component;

@Component
public class MinioClientFactory {

    public MinioClient newMinioClient(String endPoint, String accessKey, String secretKey) {
        return MinioClient.builder()
                .endpoint(endPoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
