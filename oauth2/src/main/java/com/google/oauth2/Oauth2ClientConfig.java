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

        String clientSecret = System.getenv("CLIENT_SECRET");
        String clientId = System.getenv("CLIENT_ID");
        String authorizationUri = System.getenv("AUTHORIZATION_URI");
        String tokenUri = System.getenv("TOKEN_URI");

        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("google_auth")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://host.docker.internal:8081/mailauthentication/receiveauthorizationcode")
                .redirectUri("http://host.docker.internal:8081/mailauthentication/receiveaccesstoken")
                .scope("https://www.googleapis.com/auth/gmail.send")
                .authorizationUri(authorizationUri)
                .tokenUri(tokenUri)
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
