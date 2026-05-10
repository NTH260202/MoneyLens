package com.example.moneylens.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppCode {
    SUCCESS("000", "SUCCESS");

    private final String responseCode;
    private final String messageCode;
}
