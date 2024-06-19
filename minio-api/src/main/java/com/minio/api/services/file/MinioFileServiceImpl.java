package com.minio.api.services.file;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Iterator;

@Service
public class MinioFileServiceImpl implements MinioFileService {

    private MinioClient minioClient;

    public MinioFileServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

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

    public String getFileUrl(String bucketName, String objectName) {
        try {
            String fileUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .build());
            return fileUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    public String downloadFile(String bucketName, String objectName, String path) {
        try {
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename(path + objectName)
                            .build());
            return "File downloaded successfully";
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }

    public String listFiles(String bucketName) {
        StringBuilder result = new StringBuilder();
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).recursive(true).build());
            Iterator<Result<Item>> iterator = results.iterator();

            while (iterator.hasNext()) {
                result.append(iterator.next().get().objectName()).append("\n");
            }
            return "Files listed successfully:\n" + result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String deleteFile(String bucketName, String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            return e.getMessage();
        }
        return "File deleted successfully";
    }
}
