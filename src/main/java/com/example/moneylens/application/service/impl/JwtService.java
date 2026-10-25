package com.example.moneylens.application.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@Getter
public class JwtService {

    private static final String CLAIM_TYPE = "type";
    private static final String TYPE_ACCESS = "access";
    private static final String TYPE_REFRESH = "refresh";

    private final SecretKey key;
    private final long ttlSeconds;          // thời hạn access token
    private final long refreshTtlSeconds;   // thời hạn refresh token

    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.ttl-seconds}") long ttlSeconds,
                      @Value("${app.jwt.refresh-ttl-seconds:2592000}") long refreshTtlSeconds) {
        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
        if (bytes.length < 32) {
            throw new IllegalStateException("app.jwt.secret (JWT_SECRET) phải dài ít nhất 32 byte");
        }
        this.key = Keys.hmacShaKeyFor(bytes);
        this.ttlSeconds = ttlSeconds;
        this.refreshTtlSeconds = refreshTtlSeconds;
    }

    /** Tạo access token (dùng để gọi API). */
    public String generate(String userId, String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(userId)
                .claim("username", username)
                .claim(CLAIM_TYPE, TYPE_ACCESS)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(ttlSeconds)))
                .signWith(key)
                .compact();
    }

    /** Tạo refresh token (chỉ dùng để gọi /refresh). */
    public String generateRefresh(String userId) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(userId)
                .claim(CLAIM_TYPE, TYPE_REFRESH)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(refreshTtlSeconds)))
                .signWith(key)
                .compact();
    }

    /**
     * Trả về userId nếu là access token hợp lệ, ngược lại rỗng.
     * Refresh token bị từ chối, nên không thể dùng refresh token để gọi API.
     * Token không có claim "type" (phát hành trước khi sửa) vẫn được coi là access token.
     */
    public Optional<String> parseUserId(String token) {
        return parse(token)
                .filter(c -> {
                    String type = c.get(CLAIM_TYPE, String.class);
                    return type == null || TYPE_ACCESS.equals(type);
                })
                .map(Claims::getSubject);
    }

    /** Trả về userId nếu là refresh token hợp lệ, ngược lại rỗng. */
    public Optional<String> parseRefreshUserId(String token) {
        return parse(token)
                .filter(c -> TYPE_REFRESH.equals(c.get(CLAIM_TYPE, String.class)))
                .map(Claims::getSubject);
    }

    private Optional<Claims> parse(String token) {
        try {
            return Optional.of(Jwts.parser().verifyWith(key).build()
                    .parseSignedClaims(token).getPayload());
        } catch (JwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}