package com.example.moneylens.model.response;

import com.example.moneylens.common.constant.Type;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private String transactionCode;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime transactionDate;
    private String description;
    private Type type;
    private CategoryResponse categoryResponse;
}
