package com.minio.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bucket")
public class MinioBucketController {

    @Autowired
    private MinioFileClient minioFileClient;

    @Autowired
    private MinioBucketClient minioBucketClient;

    @PutMapping("/create")
    public ResponseEntity<String> createBucket(@RequestParam final String bucketName) {
        return minioBucketClient.createBucket(bucketName);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBucket(@RequestParam final String bucketName) {
        return minioBucketClient.deleteBucket(bucketName);
    }

}
