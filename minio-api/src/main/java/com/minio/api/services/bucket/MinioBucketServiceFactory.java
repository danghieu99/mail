package com.minio.api.services.bucket;

public interface MinioBucketServiceFactory {

    public MinioBucketService getMinioBucketService(String id);
}
