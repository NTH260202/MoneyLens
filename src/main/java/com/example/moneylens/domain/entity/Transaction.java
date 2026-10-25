package com.example.moneylens.domain.entity;

import com.example.moneylens.application.common.constant.Source;
import com.example.moneylens.application.common.constant.Type;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {
    @Id
    private String transactionCode;

    private BigDecimal amount;

    private String currency;

    private Instant transactionDate;

    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Source source;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "category_code")
    private Category category;
}
