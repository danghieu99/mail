package com.minio.api.service.impl;

import com.minio.api.entity.MinioCredentials;
import io.minio.MinioClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class MinioClientFactory {

    public MinioClient newMinioClient(@NotNull MinioCredentials credentials) {
        return MinioClient.builder()
                .endpoint(credentials.getEndPoint())
                .credentials(credentials.getAccessKey(), credentials.getSecretKey())
                .build();
    }
}
