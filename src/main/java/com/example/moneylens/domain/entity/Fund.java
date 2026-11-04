package com.example.moneylens.domain.entity;

import com.example.moneylens.application.common.constant.FundType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fund extends BaseEntity {
    @Id
    private String id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "fund_type", length = 30)
    private FundType fundType;

    @Column(name = "from_date")
    private Instant fromDate;

    @Column(name = "to_date")
    private Instant to;

    @OneToMany(mappedBy = "fund")
    private List<FundMember> members;
}
