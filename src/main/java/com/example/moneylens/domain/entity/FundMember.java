package com.example.moneylens.domain.entity;

import com.example.moneylens.application.common.constant.FundRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundMember {
    @Id
    private String memberId;

    @ManyToOne(cascade = jakarta.persistence.CascadeType.ALL)
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

    @ManyToOne(cascade = jakarta.persistence.CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "member_name")
    private String memberName;

    @Enumerated(EnumType.STRING)
    private FundRole role;
}
