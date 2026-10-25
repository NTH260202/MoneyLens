package com.example.moneylens.application.model.response;

import com.example.moneylens.application.common.constant.Source;
import com.example.moneylens.application.common.constant.Type;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private String transactionCode;
    private BigDecimal amount;
    private String currency;
    private Instant transactionDate;
    private String description;
    private Type type;
    private Source source;
    private CategoryResponse categoryResponse;
}
