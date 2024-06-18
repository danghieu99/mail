package com.minio.api.init;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
public class MinioClientInit implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initMinioClient();
    }

    @PostConstruct
    public void initMinioClient() {

    }


}