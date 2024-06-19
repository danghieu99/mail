package com.minio.api.services.file;

public interface MinioFileServiceFactory {
    MinioFileService getMinioFileService(String id);
}
