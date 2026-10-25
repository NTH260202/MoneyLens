package com.example.moneylens.interfaces.controller;

import com.example.moneylens.application.model.common.BaseResponse;
import com.example.moneylens.application.model.request.LoginRequest;
import com.example.moneylens.application.model.request.RefreshTokenRequest;
import com.example.moneylens.application.model.response.LoginResponse;
import com.example.moneylens.application.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(BaseResponse.success(loginResponse));
    }

    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse<LoginResponse>> refresh(@RequestBody RefreshTokenRequest request) {
        LoginResponse loginResponse = authService.refreshToken(request);
        return ResponseEntity.ok(BaseResponse.success(loginResponse));
    }
}
