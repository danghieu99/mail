package com.minio.api.services.bucket;

import com.minio.api.services.client.MinioClientFactory;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveBucketArgs;

public class MinioBucketServiceImpl implements MinioBucketService {

    MinioClientFactory minioClientFactory;

    private MinioClient minioClient;

    public MinioBucketServiceImpl(String id) {
        this.minioClient = minioClientFactory.newMinioClient(id);
    }

    public String createBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
        return "Bucket created successfully";
    }

    public String deleteBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
        return "Bucket deleted successfully";
    }
}
