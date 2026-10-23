package com.example.moneylens.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Column(length = 100)
    private String nameEn;

    @Column(length = 100)
    private String nameVn;
}
