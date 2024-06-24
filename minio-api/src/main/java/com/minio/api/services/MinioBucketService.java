package com.minio.api.services;

public interface MinioBucketService {

    String createBucket(String bucketName);

    String deleteBucket(String bucketName);

}
