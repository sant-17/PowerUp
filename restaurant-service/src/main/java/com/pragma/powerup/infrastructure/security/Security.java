package com.pragma.powerup.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/user/client**").permitAll()
               // .antMatchers("/swagger-ui/").permitAll()
                 .antMatchers("/api/v1/auth/**", "/swagger-ui/**", "/swagger-resources/**", "/api/v1/user/client/**", "/v3/api-docs/**", "/v2/api-docs/**").permitAll()
                .antMatchers("/user/proprietary").hasAuthority("ROLE_ADMINISTRADOR")
                .antMatchers("/api/restaurant").hasAuthority("PROPIETARIO")
           //  .antMatchers("/user/employee/{idRole}").hasAuthority("ROLE_PROPIETARIO")
                .antMatchers("/square/createPlate/").hasAuthority("ROLE_PROPIETARIO")
                .antMatchers("/square/putPlate/").hasAuthority("ROLE_PROPIETARIO")
                .antMatchers("/square/putActivate/").hasAuthority("ROLE_PROPIETARIO")
                .antMatchers("/square/createEmployee").permitAll()
                .antMatchers("/user/employee/3").permitAll()
                .antMatchers("/square/allRestaurant").hasAuthority("ROLE_CLIENTE")
                .antMatchers("/square/allPlates").hasAuthority("ROLE_CLIENTE")
             //   .antMatchers("/square/allPlates**").permitAll()


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


}