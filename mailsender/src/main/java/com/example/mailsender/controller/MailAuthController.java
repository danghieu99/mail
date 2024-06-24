package com.example.mailsender.controller;

import com.example.mailsender.service.MailAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/auth")
public class MailAuthController {

    @Autowired
    private MailAuth mailAuth;

    @PostMapping("/requesttoken")
    public String authenticate(@RequestParam String username, @RequestParam String password) {
        return mailAuth.requestAccessToken(username, password);
    }

    @PostMapping("/receivetoken")
    private String receiveAccessToken(@RequestParam String token) {
        return mailAuth.receiveAccessToken(token);
    }
}
