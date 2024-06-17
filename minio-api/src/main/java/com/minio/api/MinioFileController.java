package com.minio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class MinioFileController {
    @Autowired
    private MinioFileClient minioFileClient;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return minioFileClient.uploadFile(file);
    }

    @GetMapping("/geturl")
    public ResponseEntity<String> getUrl(@RequestParam("bucketname") String bucketName,
                                         @RequestParam("filename") String fileName) {

        return minioFileClient.getFileUrl(bucketName, fileName);
    }

    @GetMapping("/download")
    public ResponseEntity<String> downloadFile(@RequestParam("filename") String fileName,
                                               @RequestParam("bucketname") String bucketName) {
        return minioFileClient.downloadFile(fileName, bucketName);
    }

    @GetMapping("/list")
    public ResponseEntity<String> listFiles(@RequestParam("bucketname") String bucketName) {
        return minioFileClient.listFiles(bucketName);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteFiles(@RequestParam("bucketname") String bucketName, @RequestParam("filename") String fileName) {
        return minioFileClient.deleteFile(fileName, bucketName);
    }
}
