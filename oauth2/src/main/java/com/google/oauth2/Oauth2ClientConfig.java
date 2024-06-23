package com.google.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Oauth2ClientConfig {

    //add securityfilterchain

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("google_auth")
                .clientId("993587433485-ikigka0qg4osb33nio2dfmjppccnagpl.apps.googleusercontent.com")
                .clientSecret("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")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("host.docker.internal:8082/api/mail/token")
                .scope("https://www.googleapis.com/auth/gmail.send", "https://www.googleapis.com/auth/userinfo.profile")
                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
                .tokenUri("https://oauth2.googleapis.com/token")
                .build();

        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
