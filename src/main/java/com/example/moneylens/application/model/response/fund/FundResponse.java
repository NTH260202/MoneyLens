package com.example.moneylens.application.model.response.fund;

import com.example.moneylens.application.common.constant.FundStatus;
import com.example.moneylens.application.common.constant.FundType;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundResponse {
    private String id;

    private String name;

    private FundType fundType;

    private Instant fromDate;

    private Instant toDate;

    private FundStatus status;
}
