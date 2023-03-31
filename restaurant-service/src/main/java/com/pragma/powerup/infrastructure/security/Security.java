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
                .antMatchers("/swagger-ui/*", "/v3/**").permitAll()


                //HU1
                .antMatchers("/api/v1/user/create-owner").hasAuthority("ADMINISTRADOR")

                //HU2
                .antMatchers("/api/v1/restaurant/create").hasAuthority("ADMINISTRADOR")

                //HU3
                .antMatchers("/api/v1/dish/create").hasAuthority("PROPIETARIO")

                //HU4
                .antMatchers("/api/v1/dish/update/**").hasAuthority("PROPIETARIO")

                //HU5
                .antMatchers("/api/v1/auth/**").permitAll()

                //HU6
                .antMatchers("/api/v1/employee/create").hasAuthority("PROPIETARIO")

                //HU7
                .antMatchers("/api/v1/dish/active/**").hasAuthority("PROPIETARIO")

                //HU8
                .antMatchers("/api/v1/user/create-client").permitAll()

                //HU9
                .antMatchers("/api/v1/restaurant/all/**").permitAll()

                //HU10
                .antMatchers("/api/v1/dish/restaurant/**").permitAll()

                //HU11
                .antMatchers("/api/v1/order/create").hasAuthority("CLIENTE")

                //HU12
                .antMatchers("/api/v1/order/status/**").hasAuthority("EMPLEADO")

                //HU13
                .antMatchers("/api/v1/order/set_chef/**").hasAuthority("EMPLEADO")

                //HU14
                .antMatchers("/api/v1/order/set_ready/**").hasAuthority("EMPLEADO")

                //HU15
                .antMatchers("/api/v1/order/set_delivered/**").hasAuthority("EMPLEADO")

                //HU16
                .antMatchers("/api/v1/order/cancel/**").hasAuthority("CLIENTE")

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