package com.minio.api.init;

import com.minio.api.services.minioconfig.MinioConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MinioConfigsInit implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    MinioConfigService minioConfigService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initMinioCredentials();
    }

    public void initMinioCredentials() {
        String endPoint = "http://118.71.99.251:9095/";
        String accessKey = "ERnGCPDwwkhCObagKdiA";
        String secretKey = "zf1yGIuCP2JVw6ssVubn7b6ZtUkMRHtENWtkgvl1";

        minioConfigService.addConfig(endPoint, accessKey, secretKey);
    }
}
