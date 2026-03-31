package com.example.hrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Tắt CSRF để có thể gọi POST/PUT từ Postman mà không cần token
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Cấu hình quyền truy cập
                .authorizeHttpRequests(auth -> auth
                        // Cho phép tất cả các request bắt đầu bằng /api/
                        .requestMatchers("/api/**").permitAll()
                        // Hoặc đơn giản là cho phép tất cả mọi thứ để test nhanh
                        .anyRequest().permitAll()
                )

                // 3. Tắt form login mặc định của Spring
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}