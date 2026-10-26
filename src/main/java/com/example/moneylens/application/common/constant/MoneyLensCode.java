package com.example.moneylens.application.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MoneyLensCode {
    SUCCESS("000", "SUCCESS"),

    /*
     * Category
     */
    CATEGORY_NOT_FOUND("CATE-001", "CATEGORY_NOT_FOUND"),
    CATEGORY_LIMIT_EXCEEDED("CATE-002", "CATEGORY_LIMIT_EXCEEDED"),

    /*
     * Category
     */
    TRANSACTION_NOT_FOUND("TRANS-001", "TRANSACTION_NOT_FOUND");

    private final String responseCode;
    private final String messageCode;
}
