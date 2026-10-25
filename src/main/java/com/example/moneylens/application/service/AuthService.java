package com.example.moneylens.application.service;

import com.example.moneylens.application.model.request.LoginRequest;
import com.example.moneylens.application.model.request.RefreshTokenRequest;
import com.example.moneylens.application.model.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshToken(RefreshTokenRequest request);
}
