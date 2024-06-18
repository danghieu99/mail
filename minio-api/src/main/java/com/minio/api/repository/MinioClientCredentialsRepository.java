package com.minio.api.repository;

import com.minio.api.entities.MinioClientCredentials;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories(basePackageClasses = MinioClientCredentials.class)
public interface MinioClientCredentialsRepository extends ListCrudRepository<MinioClientCredentials, String> {

    @NotNull
    Optional<MinioClientCredentials> findById(String clientId);

    List<MinioClientCredentials> findAllByEndPoint(String endpoint);

    List<MinioClientCredentials> findAllByAccessKey(String endpoint, String key);

}
