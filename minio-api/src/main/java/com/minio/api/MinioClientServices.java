package com.minio.api;

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
public class MinioClientServices {

    @Autowired
    MinioClient minioClient;

    public ResponseEntity<String> uploadFile(MultipartFile file) {
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
            return ResponseEntity.ok("File uploaded successfully as " + objectName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
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

    public ResponseEntity<String> downloadFile(String objectName, String bucketName) {
        try {
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename("c:/users/hieudtt/Downloads/" + objectName)
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

    public ResponseEntity<String> createBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok("Bucket created successfully");
    }

    public ResponseEntity<String> deleteBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok("Bucket deleted successfully");
    }
}
