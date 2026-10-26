package com.example.moneylens.application.common.utils;

import com.example.moneylens.application.model.AppUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class AuthUtils {
    private AuthUtils() {
    }

    /** User đang đăng nhập; rỗng nếu chưa đăng nhập (ví dụ API public, job chạy nền). */
    public static Optional<AppUserDetails> currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof AppUserDetails details) {
            return Optional.of(details);
        }
        return Optional.empty();
    }

    /** userId đang đăng nhập; rỗng nếu chưa đăng nhập. */
    public static Optional<String> currentUserId() {
        return currentUser().map(AppUserDetails::getUserId);
    }

    /** userId đang đăng nhập; ném 401 nếu chưa đăng nhập. Dùng trong các API bắt buộc đăng nhập. */
    public static String requireUserId() {
        return currentUserId()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Chưa đăng nhập"));
    }
}
