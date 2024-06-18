package com.minio.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "credentials")
public class MinioCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "endpoint", nullable = false)
    private String endPoint;
    @Column(name = "accesskey", nullable = false, unique = true)
    private String accessKey;
    @Column(name = "secretkey", nullable = false, unique = true)
    private String secretKey;

    public MinioCredentials() {
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
