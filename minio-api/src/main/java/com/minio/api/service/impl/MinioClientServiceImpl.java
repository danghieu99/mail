package com.minio.api.service.impl;

import com.minio.api.repository.MinioCredentialsRepository;
import com.minio.api.service.MinioClientService;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinioClientServiceImpl implements MinioClientService {
    @Override
    public String deleteClientById(Long id) {


        return "";
    }

    @Override
    public String listClients() {


        return "";
    }

    @Autowired
    private MinioClientFactory minioClientFactory;

    @Autowired
    private MinioCredentialsRepository minioCredentialsRepository;

    public String createClient(String endPoint, String accessKey, String secretKey) {
        MinioClient client = minioClientFactory.newMinioClient(endPoint, accessKey, secretKey);
        minioCredentialsRepository.save(client);
        return "client created";
    }
}
