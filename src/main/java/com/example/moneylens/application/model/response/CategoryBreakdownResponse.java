package com.example.moneylens.application.model.response;

import com.example.moneylens.application.common.constant.Type;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record CategoryBreakdownResponse(Type type, Instant from, Instant to,
                                        BigDecimal total, List<Item> categories) {

    public record Item(String code, String icon, String color,
                       BigDecimal total, long count, BigDecimal percentage) {
    }
}