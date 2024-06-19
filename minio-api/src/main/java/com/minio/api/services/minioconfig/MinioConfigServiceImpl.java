package com.minio.api.services.minioconfig;

import com.minio.api.entity.MinioConfig;
import com.minio.api.repository.MinioConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MinioConfigServiceImpl implements MinioConfigService {

    @Autowired
    private MinioConfigRepository minioConfigRepository;

    @Override
    public String listConfigs() {
        Iterable<MinioConfig> credentials = minioConfigRepository.findAll();
        StringBuilder clientList = new StringBuilder();
        while (credentials.iterator().hasNext()) {
            clientList.append(credentials.iterator().next()).append("\n");
        }
        return clientList.toString();
    }

    @Override
    public String deleteConfigById(Long id) {
        try {
            minioConfigRepository.deleteById(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "client config deleted";
    }

    public String addConfig(String endPoint, String accessKey, String secretKey) {
        minioConfigRepository.save(new MinioConfig(endPoint, accessKey, secretKey));
        return "client config created";
    }

    @Override
    public MinioConfig getConfigById(Long id) {
        Optional<MinioConfig> opt = minioConfigRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }
}
