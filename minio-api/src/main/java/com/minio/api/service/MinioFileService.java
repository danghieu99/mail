package com.minio.api.service;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Iterator;

@Service
public class MinioFileService {

    @Autowired
    io.minio.MinioClient minioClient;

    public String uploadFile(MultipartFile file) {
        try {
            String bucketName = "newbucket";
            String objectName = file.getOriginalFilename();

            try (InputStream inputStream = file.getInputStream()) {
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(objectName)
                                .stream(inputStream, file.getSize(), -1)
                                .contentType("multipart/form-data")
                                .build()
                );
            }
            return objectName;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public ResponseEntity<String> getFileUrl(String bucketName, String objectName) {
        try {
            String fileUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .build());
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> downloadFile(String bucketName, String objectName) {
        try {
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename("C:\\Users\\hieudtt\\Downloads\\" + objectName)
                            .build());
            return ResponseEntity.ok("File downloaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> listFiles(String bucketName) {
        String result = "";
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).recursive(true).build());
            Iterator<Result<Item>> iterator = results.iterator();

            while (iterator.hasNext()) {
                result += iterator.next().get().objectName() + "\n";
            }
            return ResponseEntity.ok("Files listed successfully:\n" + result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
    }

    public ResponseEntity<String> deleteFile(String bucketName, String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok("File deleted successfully");
    }
}
