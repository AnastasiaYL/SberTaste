package com.example.sbertaste.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig implements WebMvcConfigurer {
    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/pizza/**").permitAll()
                .antMatchers("/order/**").permitAll()
                .antMatchers(HttpMethod.POST, "/pizza").hasAnyAuthority("MANAGER", "BOSS")
                .antMatchers(HttpMethod.PUT, "/pizza/**").hasAnyAuthority("MANAGER", "BOSS")
                .antMatchers(HttpMethod.DELETE, "/pizza/**").hasAnyAuthority("MANAGER", "BOSS")
                .antMatchers("/customer/**").hasAnyAuthority("MANAGER", "BOSS")
                .antMatchers("/user/**").hasAuthority("BOSS")
                .antMatchers("/role/**").hasAuthority("BOSS")
                .antMatchers("/statistic/**").hasAuthority("BOSS")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                            authException.getMessage());
                })
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
