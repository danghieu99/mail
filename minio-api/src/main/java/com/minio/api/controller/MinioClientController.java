package com.minio.api.controller;

import com.minio.api.service.MinioClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class MinioClientController {

    @Autowired
    private MinioClientService minioClientService;

    @PostMapping("/create")
    public String createClient(@RequestParam("endpoint") String endPoint,
                               @RequestParam("accessKey") String accessKey,
                               @RequestParam("secretKey") String secretKey) {
        return minioClientService.createClient(endPoint, accessKey, secretKey);
    }

    @GetMapping("/list")
    public String listClient() {
        return minioClientService.listClients();
    }

    @DeleteMapping("/delete")
    public String deleteClient(Long id) {
        return minioClientService.deleteClientById(id);
    }
}
