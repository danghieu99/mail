package com.minio.api.services.bucketservice;

public interface MinioBucketService {

    String createBucket(String bucketName);

    String deleteBucket(String bucketName);

}
