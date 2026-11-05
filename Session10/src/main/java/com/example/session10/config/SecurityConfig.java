package com.example.session10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 1. Bean mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Bean cấu hình Spring Security
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                // Tắt CSRF cho bài thực hành REST API
                .csrf(csrf -> csrf.disable())

                // Phân quyền API
                .authorizeHttpRequests(auth -> auth

                        // Public API
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()

                        // Các API còn lại cần xác thực
                        .anyRequest()
                        .authenticated()
                )

                // Tắt Form Login mặc định
                .formLogin(form -> form.disable())

                // Tắt HTTP Basic cho bài này
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}