package com.example.moneylens.application.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MoneyLensCode {
    SUCCESS("000", "SUCCESS", HttpStatus.OK),

    /**
     * Common
     */
    UNAUTHORIZED("COMM-001", "UNAUTHORIZED", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("COMM-002", "FORBIDDEN", HttpStatus.FORBIDDEN),

    /**
     * Category
     */
    CATEGORY_NOT_FOUND("CATE-001", "CATEGORY_NOT_FOUND", HttpStatus.NOT_FOUND),
    CATEGORY_LIMIT_EXCEEDED("CATE-002", "CATEGORY_LIMIT_EXCEEDED", HttpStatus.BAD_REQUEST),

    /**
     * Category
     */
    TRANSACTION_NOT_FOUND("TRANS-001", "TRANSACTION_NOT_FOUND", HttpStatus.NOT_FOUND),

    /**
     * Fund
     */
    FUND_OWNER_ALREADY_EXISTS("FUND-001", "FUND_OWNER_ALREADY_EXISTS", HttpStatus.BAD_REQUEST),

    ;

    private final String responseCode;
    private final String messageCode;
    private final HttpStatus httpStatus;
}
