package com.minio.api.init;

import com.minio.api.entity.MinioCredentials;
import com.minio.api.repository.MinioCredentialsRepository;
import com.minio.api.service.MinioClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
public class MinioClientInit implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    MinioClientService minioClientService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initMinioCredentials();
    }

    public void initMinioCredentials() {
        String endPoint = "http://118.71.99.251:9095/";
        String accessKey = "ERnGCPDwwkhCObagKdiA";
        String secretKey = "zf1yGIuCP2JVw6ssVubn7b6ZtUkMRHtENWtkgvl1";

        minioClientService.addClient(endPoint, accessKey, secretKey);
    }
}
