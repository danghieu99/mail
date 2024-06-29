package com.example.mailsender.service;

import com.example.mailsender.dto.MailData;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface MailService {

    String sendMailWithAttachmentFiles(String from, List<String> to, String subject, String content, Collection<MultipartFile> files,
                                       List<String> cc, List<String> bcc, List<String> replyTo);

    String sendMailJson(String mailJson);

    String sendMail(MailData mailData);
}
