package com.example.moneylens.infrastructure.auth;

import com.example.moneylens.application.common.constant.MoneyLensCode;
import com.example.moneylens.application.model.common.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        BaseResponse<Object> res = new BaseResponse<>() {};
        res.setCode(MoneyLensCode.UNAUTHORIZED.getResponseCode());
        res.setMessage(MoneyLensCode.UNAUTHORIZED.getMessageCode());

        response.setStatus(MoneyLensCode.UNAUTHORIZED.getHttpStatus().value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}
