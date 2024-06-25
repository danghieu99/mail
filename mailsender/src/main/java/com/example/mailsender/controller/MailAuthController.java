package com.example.mailsender.controller;

import com.example.mailsender.service.mailauth.MailAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/mailauthentication")
public class MailAuthController {

    @Autowired
    private MailAuth mailAuth;

    @GetMapping("/authorization_code")
    public String receiveAuthorizationCode(@RequestParam("code") String authorizationCode) {
        return authorizationCode;
    }

    @GetMapping("/getgoogleauthenticationurl")
    public String requestAuthorizationCode() {
        return mailAuth.fetchGoogleAuthenticationUrl();
    }

    @PostMapping("/gettaccesstoken")
    public String requestAccessToken(@RequestParam("authorization_code") String authorizationCode) {
        return mailAuth.fetchAccessToken(authorizationCode);
    }
}
