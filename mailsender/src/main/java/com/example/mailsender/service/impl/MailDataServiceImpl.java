package com.example.mailsender.service.impl;

import com.example.mailsender.dto.MailData;
import com.example.mailsender.service.MailDataService;
import com.example.mailsender.service.MinioFileClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;

@Service
public class MailDataServiceImpl implements MailDataService {

    private MinioFileClient minioFileClient;

    @Autowired
    public MailDataServiceImpl(MinioFileClient minioFileClient) {
        this.minioFileClient = minioFileClient;
    }

    @Override
    public MailData createMailData(String from, Collection<String> to, String subject, String body, Collection<MultipartFile> files,
                                   Collection<String> cc, Collection<String> bcc, Collection<String> replyTo) {
        HashMap<String, String> attachments = minioFileClient.uploadAttachmentFiles(files);
        return MailData.from(from).to(to).subject(subject).body(body).attachments(attachments).cc(cc).bcc(bcc).replyTo(replyTo).build();
    }
}
