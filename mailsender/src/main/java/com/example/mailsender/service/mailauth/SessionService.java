package com.example.mailsender.service.mailauth;

import jakarta.mail.Session;

public interface SessionService {

    public Session createSession(String username, String token);


}
