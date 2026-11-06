package com.example.moneylens.application.model.response.auth;

import java.time.Instant;

public record UserResponse(String id, String username, String email, Instant createdAt) {
}
