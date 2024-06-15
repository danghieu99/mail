package com.minio.api;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientConfig {
    private final String apiUrl = "http://118.71.99.251:9095/";
    private final String accessKey = "ERnGCPDwwkhCObagKdiA";
    private final String secretKey = "zf1yGIuCP2JVw6ssVubn7b6ZtUkMRHtENWtkgvl1";

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(apiUrl)
                .credentials(accessKey, secretKey)
                .build();
    }
}
