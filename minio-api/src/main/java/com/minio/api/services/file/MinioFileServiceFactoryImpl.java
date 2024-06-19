package com.minio.api.services.file;

import org.springframework.stereotype.Service;

@Service
public class MinioFileServiceFactoryImpl implements MinioFileServiceFactory {
    @Override
    public MinioFileService getMinioFileService(String id) {
        return new MinioFileServiceImpl(id);
    }
}
