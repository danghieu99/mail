package com.minio.api.service;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinioClientService {

    @Autowired
    private MinioClientFactory minioClientFactory;

    public MinioClient createClient(String endPoint, String accessKey, String secretKey) {
        return minioClientFactory.newMinioClient(endPoint, accessKey, secretKey);
    }

}
