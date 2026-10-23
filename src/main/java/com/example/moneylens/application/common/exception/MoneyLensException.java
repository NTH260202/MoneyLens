package com.example.moneylens.application.common.exception;

import com.example.moneylens.application.common.constant.MoneyLensCode;
import lombok.Getter;

@Getter
public class MoneyLensException extends RuntimeException {
    private final MoneyLensCode errorCode;

    public MoneyLensException(String message, MoneyLensCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MoneyLensException(MoneyLensCode errorCode) {

        super(errorCode.getMessageCode());

        this.errorCode = errorCode;
    }
}
