package com.minio.api.config;

import com.minio.api.entity.MinioCredentials;
import com.minio.api.service.impl.MinioClientFactory;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientConfig {

    private final MinioCredentials defaultCredentials = new MinioCredentials("http://118.71.99.251:9095/",
            "ERnGCPDwwkhCObagKdiA",
            "zf1yGIuCP2JVw6ssVubn7b6ZtUkMRHtENWtkgvl1");

    @Autowired
    MinioClientFactory minioClientFactory;

    @Bean
    public MinioClient getDefaultMinioClient() {
        return minioClientFactory.newMinioClient(defaultCredentials);
    }
}
