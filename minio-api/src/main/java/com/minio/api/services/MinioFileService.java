package com.minio.api.services;

import org.springframework.web.multipart.MultipartFile;

public interface MinioFileService {
    String uploadFile(MultipartFile file);

    String getFileUrl(String bucketName, String objectName);

    String downloadFile(String bucketName, String objectName, String path);

    String listFiles(String bucketName);

    String deleteFile(String bucketName, String objectName);
}
