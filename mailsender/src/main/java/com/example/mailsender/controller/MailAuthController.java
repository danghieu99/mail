package com.example.mailsender.controller;

import com.example.mailsender.service.mailauth.MailAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/mailauthentication")
public class MailAuthController {

    @Autowired
    private MailAuth mailAuth;

    @GetMapping("/requestauthorizationcode")
    public String requestAuthorizationCode(@RequestParam String username) {
        return mailAuth.requestAuthorizationCode(username);
    }

    @GetMapping("/requestaccesstoken")
    public String requestAccessToken(@RequestParam("authorization_code") String authorizationCode) {
        return mailAuth.requestAccessToken(authorizationCode);
    }

    @GetMapping("/receiveauthorizationcode")
    public String receiveAuthorizationCode(@RequestParam("authorization_code") String authorizationCode) {
        return mailAuth.receiveAuthorizationCode(authorizationCode);
    }

    @GetMapping("/receiveaccesstoken")
    public String receiveAccessToken(@RequestParam("access_token") String accessToken) {
        return mailAuth.receiveAccessToken(accessToken);
    }

}
