package com.google.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class Oauth2Controller {

    private Oauth2Client oauth2Client;

    @Autowired
    public Oauth2Controller(Oauth2Client oauth2Client) {
        this.oauth2Client = oauth2Client;
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return oauth2Client.fetchAccessTokenFromGoogle(username, password);
    }
}
