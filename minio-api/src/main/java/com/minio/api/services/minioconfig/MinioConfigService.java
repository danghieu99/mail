package com.minio.api.services.minioconfig;

import com.minio.api.entity.MinioConfig;

public interface MinioConfigService {

    String addConfig(String endPoint, String accessKey, String secretKey);

    String deleteConfigById(Long id);

    String listConfigs();

    MinioConfig getConfigById(Long id);

}
