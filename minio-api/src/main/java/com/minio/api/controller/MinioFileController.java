package com.minio.api.controller;

import com.minio.api.services.file.MinioFileService;
import com.minio.api.services.file.MinioFileServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file/{id}")

public class MinioFileController {

    @Autowired
    MinioFileServiceFactory minioFileServiceFactory;

    @PostMapping("/upload")
    public String uploadFile(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        MinioFileService minioFileService = minioFileServiceFactory.getMinioFileService(id);
        return minioFileService.uploadFile(file);
    }

    @GetMapping("/geturl")
    public String getUrl(@PathVariable String id,
                         @RequestParam("bucketname") String bucketName,
                         @RequestParam("filename") String fileName) {
        MinioFileService minioFileService = minioFileServiceFactory.getMinioFileService(id);
        return minioFileService.getFileUrl(bucketName, fileName);
    }

    @GetMapping("/download")
    public String downloadFile(@PathVariable String id,
                               @RequestParam("filename") String fileName,
                               @RequestParam("bucketname") String bucketName,
                               @RequestParam("path") String path) {
        MinioFileService minioFileService = minioFileServiceFactory.getMinioFileService(id);
        return minioFileService.downloadFile(bucketName, fileName, path);
    }

    @GetMapping("/list")
    public String listFiles(@PathVariable String id,
                            @RequestParam("bucketname") String bucketName) {
        MinioFileService minioFileService = minioFileServiceFactory.getMinioFileService(id);
        return minioFileService.listFiles(bucketName);
    }

    @DeleteMapping("/delete")
    public String deleteFiles(@PathVariable String id,
                              @RequestParam("bucketname") String bucketName, @RequestParam("filename") String fileName) {
        MinioFileService minioFileService = minioFileServiceFactory.getMinioFileService(id);
        return minioFileService.deleteFile(bucketName, fileName);
    }
}
