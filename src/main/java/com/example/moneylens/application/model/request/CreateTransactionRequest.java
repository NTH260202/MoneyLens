package com.example.moneylens.application.model.request;

import com.example.moneylens.application.common.constant.Source;
import com.example.moneylens.application.common.constant.Type;
import com.example.moneylens.application.common.validation.annotation.ValidCurrency;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

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
    private Instant transactionDate;

    @NotNull
    private Source source;

    @NotNull
    private Type type;
}
