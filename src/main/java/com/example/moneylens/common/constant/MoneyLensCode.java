package com.example.moneylens.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MoneyLensCode {
    SUCCESS("000", "SUCCESS"),

    /*
     * Category
     */
    CATEGORY_NOT_FOUND("001", "CATEGORY_NOT_FOUND");

    private final String responseCode;
    private final String messageCode;
}
