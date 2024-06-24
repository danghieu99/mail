package com.minio.api.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface MinioFileService {
    String uploadFile(String bucket, MultipartFile file);

    String uploadFiles(String bucket, Collection<MultipartFile> files);

    String getFileUrl(String bucketName, String objectName);

    String downloadFile(String bucketName, String objectName, String path);

    String listFiles(String bucketName);

    String deleteFile(String bucketName, String objectName);
}
