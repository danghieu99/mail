package com.example.mailsender.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public interface MinioFileClient {

    public String uploadFile(MultipartFile file, String fileName) throws IOException;

    public HashMap<String, String> uploadAttachmentFiles(Collection<MultipartFile> files);

    public String fetchFileUrl(String fileName);

}
