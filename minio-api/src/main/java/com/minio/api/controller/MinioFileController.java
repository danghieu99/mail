package com.minio.api.controller;

import com.minio.api.services.MinioFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")

public class MinioFileController {

    @Autowired
    MinioFileService minioFileService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return minioFileService.uploadFile(file);
    }

    @GetMapping("/geturl")
    public String getUrl(@RequestParam("bucketname") String bucketName,
                         @RequestParam("filename") String fileName) {
        return minioFileService.getFileUrl(bucketName, fileName);
    }

    @GetMapping("/download")
    public String downloadFile(@RequestParam("filename") String fileName,
                               @RequestParam("bucketname") String bucketName,
                               @RequestParam("path") String path) {
        return minioFileService.downloadFile(bucketName, fileName, path);
    }

    @GetMapping("/list")
    public String listFiles(@RequestParam("bucketname") String bucketName) {
        return minioFileService.listFiles(bucketName);
    }

    @DeleteMapping("/delete")
    public String deleteFiles(@RequestParam("bucketname") String bucketName, @RequestParam("filename") String fileName) {
        return minioFileService.deleteFile(bucketName, fileName);
    }
}
