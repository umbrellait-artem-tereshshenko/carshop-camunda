package com.umbrellait.carshop_camunda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Spring Security configuration for REST API
 *
 * @author artem.tereshchenko
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers(HttpMethod.GET, "/sales").hasRole("SALESMAN")
                .requestMatchers(HttpMethod.POST, "/sales").hasRole("SALESMAN")
                .requestMatchers(HttpMethod.POST, "/customer").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/customer").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/bank").hasRole("BANK")
                .requestMatchers(HttpMethod.POST, "/bank").hasRole("BANK")
                .anyRequest().authenticated());
        http.csrf(AbstractHttpConfigurer::disable);
        http.exceptionHandling(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);

        return authenticationProvider;
    }

}