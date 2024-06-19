package com.minio.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "credentials")
public class MinioConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "endpoint", nullable = false)
    private String endPoint;
    @Column(name = "accesskey", nullable = false, unique = true)
    private String accessKey;
    @Column(name = "secretkey", nullable = false, unique = true)
    private String secretKey;

    public MinioConfig() {
    }

    public MinioConfig(String endPoint, String accessKey, String secretKey) {
        this.endPoint = endPoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public Long getId() {
        return id;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
