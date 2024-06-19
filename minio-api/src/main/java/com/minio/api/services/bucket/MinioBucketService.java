package com.minio.api.services.bucket;

import org.springframework.web.context.annotation.RequestScope;

@RequestScope
public interface MinioBucketService {

    String createBucket(String bucketName);

    String deleteBucket(String bucketName);

}
