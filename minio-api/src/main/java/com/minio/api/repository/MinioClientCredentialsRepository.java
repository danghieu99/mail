package com.minio.api.repository;

import com.minio.api.entities.MinioClientCredentials;
import org.springframework.data.repository.ListCrudRepository;

public interface MinioClientCredentialsRepository
        extends ListCrudRepository<MinioClientCredentials, String> {


}
