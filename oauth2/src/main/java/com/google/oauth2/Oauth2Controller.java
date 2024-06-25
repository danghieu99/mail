package com.google.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth2")
public class Oauth2Controller {

    private Oauth2Client oauth2Client;

    @Autowired
    public Oauth2Controller(Oauth2Client oauth2Client) {
        this.oauth2Client = oauth2Client;
    }

    @PostMapping("/auth")
    public String grantAuthorization(String username) {
        return oauth2Client.requestAuthorizationCodeFromGoogle(username);
    }

    @PostMapping("/token")
    public String grantToken(@RequestParam("authorization_code") String authorizationCode) {
        return oauth2Client.requestAccessTokenFromGoogle(authorizationCode);
    }
}
