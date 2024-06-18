package com.minio.api.controller;


import com.minio.api.service.MinioBucketService;
import com.minio.api.service.MinioClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bucket")
public class MinioBucketController {

    @Autowired
    private MinioBucketService minioBucketService;

    @Autowired
    private MinioClientFactory minioClientFactory;

    @PostMapping("/create")
    public ResponseEntity<String> createBucket(@RequestParam("bucketname") final String bucketName) {
        return minioBucketService.createBucket(bucketName);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBucket(@RequestParam("bucketname") final String bucketName) {
        return minioBucketService.deleteBucket(bucketName);
    }

}
