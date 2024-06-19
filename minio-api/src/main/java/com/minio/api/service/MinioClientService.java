package com.minio.api.service;

import io.minio.MinioClient;

public interface MinioClientService {

    String addClient(String endPoint, String accessKey, String secretKey);

    String deleteClientById(Long id);

    String listClients();

    MinioClient getClient(String id);
}
