package com.minio.api.services.bucket;

import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveBucketArgs;
import org.springframework.stereotype.Service;

@Service
public class MinioBucketServiceImpl implements MinioBucketService {

    private MinioClient minioClient;

    public MinioBucketServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
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
