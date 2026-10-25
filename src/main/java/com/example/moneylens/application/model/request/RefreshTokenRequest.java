package com.example.moneylens.application.model.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String accessToken;
    private String refreshToken;
}
