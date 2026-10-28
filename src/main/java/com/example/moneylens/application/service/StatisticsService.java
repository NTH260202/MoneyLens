package com.example.moneylens.application.service;

import com.example.moneylens.application.common.constant.Type;
import com.example.moneylens.application.model.response.CategoryBreakdownResponse;

import java.time.Instant;

public interface StatisticsService {
    CategoryBreakdownResponse byCategory(Type type, Instant from, Instant to);
}
