package com.example.mailsender.services.common.minioapiclient;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public interface MinioFileClient {

    String uploadFile(MultipartFile file, String fileName) throws IOException;

    HashMap<String, String> uploadAttachmentFiles(Collection<MultipartFile> files);

    String fetchFileUrl(String fileName);

}
