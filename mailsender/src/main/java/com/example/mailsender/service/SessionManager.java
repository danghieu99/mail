package com.example.mailsender.service;

import jakarta.mail.Session;

public interface SessionManager {

    public String addSession(String username, String token);

    public Session retrieveSession(String username);

    public Session createSession(String username, String token);

}
