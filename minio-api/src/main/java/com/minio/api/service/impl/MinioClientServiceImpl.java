package com.minio.api.service.impl;

import com.minio.api.entity.MinioCredentials;
import com.minio.api.repository.MinioCredentialsRepository;
import com.minio.api.service.MinioClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinioClientServiceImpl implements MinioClientService {

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

    @Autowired
    private MinioCredentialsRepository minioCredentialsRepository;

    public String createClient(String endPoint, String accessKey, String secretKey) {
        minioCredentialsRepository.save(new MinioCredentials(endPoint, accessKey, secretKey));
        return "client created";
    }
}
