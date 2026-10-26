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
public class Category extends BaseEntity {
    @Id
    @Column(length = 36)
    private String code;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String name;

    private String icon;

    private String color;
}
