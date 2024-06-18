package com.minio.api.service;

public interface MinioBucketService {

    String createBucket(String bucketName);

    String deleteBucket(String bucketName);

}
