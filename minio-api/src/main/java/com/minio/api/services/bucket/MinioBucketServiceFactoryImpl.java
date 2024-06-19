package com.minio.api.services.bucket;

import org.springframework.stereotype.Service;

@Service
public class MinioBucketServiceFactoryImpl implements MinioBucketServiceFactory {
    public MinioBucketService getMinioBucketService(String id) {
        return new MinioBucketServiceImpl(id);
    }
}
