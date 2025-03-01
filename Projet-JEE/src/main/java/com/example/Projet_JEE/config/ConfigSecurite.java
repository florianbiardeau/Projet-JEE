package com.example.Projet_JEE.config;

import com.example.Projet_JEE.service.UtilisateurService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class ConfigSecurite {

    private final UtilisateurService utilisateurService;

    public ConfigSecurite(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register**", "/login**", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()::handle)
                )
                .userDetailsService(utilisateurService);

        return http.build();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}