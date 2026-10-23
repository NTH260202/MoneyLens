package com.example.moneylens.application.model.request;

import com.example.moneylens.application.common.constant.Type;
import com.example.moneylens.application.common.validation.annotation.ValidCurrency;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
    @NotNull
    private BigDecimal amount;

    @NotNull
    @ValidCurrency
    private String currency;

    private String categoryCode;

    private String description;

    @NotNull
    private LocalDateTime transactionDate;

    @NotNull
    private Type type;
}
