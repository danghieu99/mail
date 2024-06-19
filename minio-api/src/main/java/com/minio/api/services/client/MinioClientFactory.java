package com.minio.api.services.client;

import com.minio.api.entity.MinioConfig;
import io.minio.MinioClient;
import org.jetbrains.annotations.NotNull;

public interface MinioClientFactory {

   MinioClient newMinioClient(@NotNull MinioConfig credentials);

   MinioClient newMinioClient(@NotNull String id);
}
