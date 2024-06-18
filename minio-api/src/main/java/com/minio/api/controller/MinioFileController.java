package com.minio.api.controller;

import com.minio.api.service.MinioFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class MinioFileController {

    @Autowired
    private MinioFileService minioFileService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return minioFileService.uploadFile(file);
    }

    @GetMapping("/geturl")
    public ResponseEntity<String> getUrl(@RequestParam("bucketname") String bucketName,
                                         @RequestParam("filename") String fileName) {

        return minioFileService.getFileUrl(bucketName, fileName);
    }

    @GetMapping("/download")
    public ResponseEntity<String> downloadFile(@RequestParam("filename") String fileName,
                                               @RequestParam("bucketname") String bucketName) {
        return minioFileService.downloadFile(bucketName, fileName);
    }

    @GetMapping("/list")
    public ResponseEntity<String> listFiles(@RequestParam("bucketname") String bucketName) {
        return minioFileService.listFiles(bucketName);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFiles(@RequestParam("bucketname") String bucketName, @RequestParam("filename") String fileName) {
        return minioFileService.deleteFile(bucketName, fileName);
    }
}
