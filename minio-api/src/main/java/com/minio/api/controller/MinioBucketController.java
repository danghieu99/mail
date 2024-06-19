package com.minio.api.controller;


import com.minio.api.services.bucket.MinioBucketService;
import com.minio.api.services.bucket.MinioBucketServiceFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{id}/bucket")
public class MinioBucketController {

    private MinioBucketServiceFactory minioBucketServiceFactory;

    @PostMapping("/create")
    public String createBucket(@PathVariable String id, @RequestParam("bucketname") final String bucketName) {
        MinioBucketService minioBucketService = minioBucketServiceFactory.getMinioBucketService(id);
        return minioBucketService.createBucket(bucketName);
    }

    @DeleteMapping("/delete")
    public String deleteBucket(@PathVariable String id, @RequestParam("bucketname") final String bucketName) {
        MinioBucketService minioBucketService = minioBucketServiceFactory.getMinioBucketService(id);
        return minioBucketService.deleteBucket(bucketName);
    }

}
