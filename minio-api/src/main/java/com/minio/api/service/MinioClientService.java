package com.minio.api.service;

public interface MinioClientService {

    String createClient(String endPoint, String accessKey, String secretKey);

    String deleteClientById(Long id);

    String listClients();

}
