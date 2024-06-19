package com.minio.api.repository;

import com.minio.api.entity.MinioConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableJpaRepositories(basePackages = "com.minio.api.repository")
public interface MinioConfigRepository extends CrudRepository<MinioConfig, Long> {

    Optional<MinioConfig> findById(@NotNull Long id);

    Iterable<MinioConfig> findAll();

    void deleteById(Long id);

}
