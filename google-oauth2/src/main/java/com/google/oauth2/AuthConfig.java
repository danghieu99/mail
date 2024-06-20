package com.google.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Collections;

@Configuration
public class AuthConfig {

    /*
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("mail")
                .clientId("993587433485-ikigka0qg4osb33nio2dfmjppccnagpl.apps.googleusercontent.com")
                .clientSecret("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")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://host.docker.internal/mail/{registrationId}")
                .scope("email")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .userInfoUri("https://example.com/userinfo")
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .providerConfigurationMetadata(Collections.singletonMap("end_session_endpoint", "https://example.com/logout"))
                .build();

    }

     */
