package com.minio.api.repository;

import com.minio.api.entity.MinioCredentials;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableJpaRepositories(basePackages = "com.minio.api.repository")
public interface MinioCredentialsRepository extends CrudRepository<MinioCredentials, Long> {

    @NotNull
    Optional<MinioCredentials> findById(Long id);

    Iterable<MinioCredentials> findAll();

}
