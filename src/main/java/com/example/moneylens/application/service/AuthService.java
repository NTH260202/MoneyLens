package com.example.moneylens.application.service;

import com.example.moneylens.application.model.request.auth.LoginRequest;
import com.example.moneylens.application.model.request.auth.RefreshTokenRequest;
import com.example.moneylens.application.model.response.auth.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshToken(RefreshTokenRequest request);
}
