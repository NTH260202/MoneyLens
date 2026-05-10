package com.example.moneylens.model.entity;

import com.example.moneylens.common.constant.Type;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    private String transactionCode;
    private BigDecimal amount;
    private String currency;
    private LocalDateTime transactionDate;
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "category_code")
    private Category category;
}
