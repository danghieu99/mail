package com.google.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return "";
    }

    @PutMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        return "";
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String username, @RequestParam String password) {
        return "";
    }

}
