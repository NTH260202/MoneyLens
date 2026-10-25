package com.example.moneylens.application.model.response;

public record LoginResponse(String accessToken
        , String refreshToken
        , String tokenType
        , long expiresIn
        , UserResponse user) {
}
