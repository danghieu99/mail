package com.minio.api.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientConfig {

    @Bean
    public MinioClient minioClient() {

        String endPoint = "http://118.71.99.251:9095/";
        String accessKey = "ERnGCPDwwkhCObagKdiA";
        String secretKey = "zf1yGIuCP2JVw6ssVubn7b6ZtUkMRHtENWtkgvl1";

        return MinioClient.builder()
                .endpoint(endPoint)
                .credentials(accessKey, secretKey)
                .build();

    }
}
