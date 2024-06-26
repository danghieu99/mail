package com.example.mailsender.common.services.maildata;

import com.example.mailsender.common.dtos.MailData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface MailDataService {

    MailData createMailData(String from, Collection<String> to, String subject, String body, Collection<MultipartFile> files,
                            Collection<String> cc, Collection<String> bcc, Collection<String> replyTo);
}
