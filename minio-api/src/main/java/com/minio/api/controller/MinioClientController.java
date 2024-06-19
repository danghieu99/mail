package com.minio.api.controller;

import com.minio.api.services.minioconfig.MinioConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class MinioClientController {

    @Autowired
    MinioConfigService minioConfigService;

    @PostMapping("/create")
    public String addClient(@RequestParam("endpoint") String endPoint,
                               @RequestParam("accessKey") String accessKey,
                               @RequestParam("secretKey") String secretKey) {
        return minioConfigService.addConfig(endPoint, accessKey, secretKey);
    }

    @GetMapping("/list")
    public String listClient() {
        return minioConfigService.listConfigs();
    }

    @DeleteMapping("/delete")
    public String deleteClient(Long id) {
        return minioConfigService.deleteConfigById(id);
    }
}
