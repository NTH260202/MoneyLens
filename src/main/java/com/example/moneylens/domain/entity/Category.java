package com.example.moneylens.domain.entity;

import com.example.moneylens.application.common.constant.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(length = 30)
    private String code;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String icon;

    private String color;
}
