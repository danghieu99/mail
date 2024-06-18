package com.minio.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioFileService {
    public String uploadFile(MultipartFile file);

    public String getFileUrl(String bucketName, String objectName);

    public String downloadFile(String bucketName, String objectName, String path);

    public String listFiles(String bucketName);

    public String deleteFile(String bucketName, String objectName);
}
