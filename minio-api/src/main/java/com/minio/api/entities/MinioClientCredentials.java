package com.minio.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "credentials")
public class MinioClientCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String endPoint;
    @Column(nullable = false, unique = true)
    private String accessKey;
    @Column(nullable = false, unique = true)
    private String secretKey;

    public MinioClientCredentials() {
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
