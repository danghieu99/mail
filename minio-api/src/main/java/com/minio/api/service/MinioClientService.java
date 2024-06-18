package com.minio.api.service;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;

public interface MinioClientService {

    public String createClient(String endPoint, String accessKey, String secretKey);

    public String deleteClientById(Long id);

    public String listClients();

    public String
}
