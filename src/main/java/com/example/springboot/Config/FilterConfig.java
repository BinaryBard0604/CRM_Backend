package com.example.springboot.Config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final JwtFilter jwtFilter;

    public FilterConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtFilter);

        // Apply to specific API endpoints, excluding /api/auth
        registrationBean.addUrlPatterns("/api/users/*",
                "/api/opportunities/*",
                "/api/customers/*",
                "/api/activities/*",
                "/api/teams/*");

        return registrationBean;
    }
}