package com.minio.api.service.impl;

import com.minio.api.entity.MinioCredentials;
import com.minio.api.repository.MinioCredentialsRepository;
import com.minio.api.service.MinioClientFactory;
import com.minio.api.service.MinioClientService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MinioClientServiceImpl implements MinioClientService {

    @Autowired
    private MinioCredentialsRepository minioCredentialsRepository;

    @Autowired
    private MinioClientFactory minioClientFactory;

    @Override
    public String listClients() {
        Iterable<MinioCredentials> credentials = minioCredentialsRepository.findAll();
        StringBuilder clientList = new StringBuilder();
        while (credentials.iterator().hasNext()) {
            clientList.append(credentials.iterator().next()).append("\n");
        }
        return clientList.toString();
    }

    @Override
    public String deleteClientById(Long id) {
        try {
            minioCredentialsRepository.deleteById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "delete successful";
    }

    public String addClient(String endPoint, String accessKey, String secretKey) {
        minioCredentialsRepository.save(new MinioCredentials(endPoint, accessKey, secretKey));
        return "client created";
    }

    @Override
    public MinioClient getClient(String id) {
        Optional<MinioCredentials> credentials = minioCredentialsRepository.findById(Long.valueOf(id));
        if (credentials.isPresent()) {
            return minioClientFactory.newMinioClient(credentials.get());
        }
        return null;
    }
}
