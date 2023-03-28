package com.pragma.powerup.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Security {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()

                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/user/create-client").permitAll()
                .antMatchers("/api/v1/restaurant/all/**").permitAll()
                .antMatchers("/api/v1/dish/**").hasAuthority("PROPIETARIO")
                .antMatchers("/api/v1/dish/all/**").permitAll()
                .antMatchers("/api/v1/user/create-owner").hasAuthority("ADMINISTRADOR")
                .antMatchers("/api/v1/restaurant/create").hasAuthority("ADMINISTRADOR")
                .antMatchers("/api/v1/order/").hasAuthority("CLIENTE")
                .antMatchers("/api/v1/order/get/**").hasAuthority("EMPLEADO")
                .antMatchers("/api/v1/order/set-chef/").hasAuthority("EMPLEADO")


                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .antMatchers("/swagger-ui/**");
    }

}