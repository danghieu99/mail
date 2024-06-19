package com.minio.api.service;

import com.minio.api.entity.MinioCredentials;
import io.minio.MinioClient;
import org.jetbrains.annotations.NotNull;

public interface MinioClientFactory {

   MinioClient newMinioClient(@NotNull MinioCredentials credentials);

}
