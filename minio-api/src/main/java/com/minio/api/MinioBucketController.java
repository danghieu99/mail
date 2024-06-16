package com.minio.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bucket")
public class MinioBucketController {

    @Autowired
    private MinioClient minioClient;

    @GetMapping("/create")
    public ResponseEntity<String> createBucket(@RequestParam final String bucketName) {
        return minioClient.createBucket(bucketName);
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deleteBucket(@RequestParam final String bucketName) {
        return minioClient.deleteBucket(bucketName);
    }

}
