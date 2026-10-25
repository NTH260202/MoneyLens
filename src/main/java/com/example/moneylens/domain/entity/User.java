package com.example.moneylens.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Locale;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class User {
    @Id
    private String id;

    @Column(name = "username", nullable = false, unique = true, length = 30)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 254)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected User() {
    }

    public User(String username, String email, String passwordHash) {
        this.username = username.toLowerCase(Locale.ROOT);
        this.email = email.toLowerCase(Locale.ROOT);
        this.passwordHash = passwordHash;
    }
}
