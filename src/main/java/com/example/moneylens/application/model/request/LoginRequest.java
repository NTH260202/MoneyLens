package com.example.moneylens.application.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String identifier;
    private String password;
}
