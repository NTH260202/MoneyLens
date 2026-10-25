package com.example.moneylens.application.service.impl;

import com.example.moneylens.application.model.AppUserDetails;
import com.example.moneylens.application.model.request.LoginRequest;
import com.example.moneylens.application.model.request.RefreshTokenRequest;
import com.example.moneylens.application.model.response.LoginResponse;
import com.example.moneylens.application.model.response.UserResponse;
import com.example.moneylens.application.service.AuthService;
import com.example.moneylens.domain.entity.User;
import com.example.moneylens.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    private static final int BCRYPT_MAX_BYTES = 72;

    @Override
    public LoginResponse login(LoginRequest req) {
        String identifier = req.getIdentifier().trim();

        if (tooLongForBcrypt(req.getPassword())) {
            log.error("AuthServiceImpl.login(): Invalid password format");
            throw new BadCredentialsException("Bad credentials");
        }

        try {
            Authentication auth = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(identifier, req.getPassword()));
            AppUserDetails user = ((AppUserDetails) auth.getPrincipal());
            return new LoginResponse(
                    jwtService.generate(user.getUserId(), user.getUsername()),
                    jwtService.generateRefresh(user.getUserId()),
                    "Bearer",
                    jwtService.getTtlSeconds(),
                    new UserResponse(user.getUserId(), user.getUsername(), user.getEmail(), Instant.now()));
        } catch (AuthenticationException e) {
            log.error("AuthServiceImpl.login(): Authentication failed for identifier: {}", identifier, e);
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        if (request == null || request.getRefreshToken() == null || request.getRefreshToken().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Thiếu refreshToken");
        }

        String userId = jwtService.parseRefreshUserId(request.getRefreshToken())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Refresh token không hợp lệ hoặc đã hết hạn"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Refresh token không hợp lệ hoặc đã hết hạn"));

        return new LoginResponse(
                jwtService.generate(user.getId(), user.getUsername()),
                jwtService.generateRefresh(user.getId()),
                "Bearer",
                jwtService.getTtlSeconds(),
                new UserResponse(user.getId(), user.getUsername(), user.getEmail(), Instant.now()));
    }

    private boolean tooLongForBcrypt(String password) {
        return password.getBytes(StandardCharsets.UTF_8).length > BCRYPT_MAX_BYTES;
    }
}
