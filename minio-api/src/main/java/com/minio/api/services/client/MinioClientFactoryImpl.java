package com.minio.api.services.client;

import com.minio.api.entity.MinioConfig;
import com.minio.api.services.minioconfig.MinioConfigService;
import io.minio.MinioClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class MinioClientFactoryImpl implements MinioClientFactory {

    MinioConfigService minioConfigService;

    public MinioClient newMinioClient(@NotNull MinioConfig credentials) {
        return MinioClient.builder()
                .endpoint(credentials.getEndPoint())
                .credentials(credentials.getAccessKey(), credentials.getSecretKey())
                .build();
    }

    @Override
    public MinioClient newMinioClient(@NotNull String id) {
        MinioConfig credentials = minioConfigService.getConfigById(Long.valueOf(id));
        return newMinioClient(credentials);
    }
}
