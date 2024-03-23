package com.digitalcoffee.user.configuration;

import com.digitalcoffee.commons.CustomClientHttpRequestInterceptor;
import com.digitalcoffee.commons.UserRole;
import com.digitalcoffee.user.entity.UserRoot;
import com.digitalcoffee.user.repository.UserRepository;
import com.digitalcoffee.user.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {
            "/actuator/health", "/swagger-ui/*", "/v3/api-docs/*", "/v3/api-docs",
            "/users/register", "/users/login", "/users/introspect"
    };

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->request
                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 ->
             oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder()))
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean("dcRestTemplate")
    public RestTemplate dcRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new CustomClientHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void createAdminUser(){
        if(userRepository.findByUsername("admin").isEmpty()){
            UserRoot admin = new UserRoot();
            admin.setUsername("admin");
            admin.setPassword("$2a$10$6DvxKxtP8R0PtoRxgTfKj.aA.z0fl9PTejhOTY/IHnPVc4sLwM0nO");
            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.addRole(UserRole.ADMIN);
            userRepository.save(admin);
        }
    }
}
