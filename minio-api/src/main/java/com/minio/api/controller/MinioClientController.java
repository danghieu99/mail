package com.minio.api.controller;

import com.minio.api.service.MinioClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class MinioClientController {

    @Autowired
    MinioClientFactory minioClientFactory;


    @PostMapping("/create")
    public String createClient(@RequestParam("endpoint") String endPoint,
                               @RequestParam("accessKey") String accessKey,
                               @RequestParam("secretKey") String secretKey) {
        return "client create successful";
    }

}
