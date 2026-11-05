package com.example.session10.controller;

import com.example.session10.dto.RegisterRequest;
import com.example.session10.dto.RegisterResponse;
import com.example.session10.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok(
                "API Public hoạt động thành công"
        );
    }

    // Login giả lập từ bài 2
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok(
                "Đăng nhập thành công"
        );
    }

    // API đăng ký tài khoản mới
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response =
                authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}