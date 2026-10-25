package com.example.moneylens.application.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequest {
    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;
}
