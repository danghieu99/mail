package com.minio.api.controller;


import com.minio.api.services.bucketservice.MinioBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minio/bucket")
public class MinioBucketController {

    @Autowired
    private MinioBucketService minioBucketService;

    @PostMapping("/create")
    public String createBucket(@RequestParam("bucketname") final String bucketName) {
        return minioBucketService.createBucket(bucketName);
    }

    @DeleteMapping("/delete")
    public String deleteBucket(@RequestParam("bucketname") final String bucketName) {
        return minioBucketService.deleteBucket(bucketName);
    }
}
