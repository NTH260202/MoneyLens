package com.example.moneylens.interfaces.controller;

import com.example.moneylens.application.common.constant.Type;
import com.example.moneylens.application.model.common.BaseResponse;
import com.example.moneylens.application.model.response.CategoryBreakdownResponse;
import com.example.moneylens.application.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/by-category")
    public ResponseEntity<BaseResponse<CategoryBreakdownResponse>> byCategory(
            @RequestParam Type type,
            @RequestParam(required = false) Instant from,
            @RequestParam(required = false) Instant to) {
        return ResponseEntity.ok(BaseResponse.success(statisticsService.byCategory(type, from, to)));
    }
}
