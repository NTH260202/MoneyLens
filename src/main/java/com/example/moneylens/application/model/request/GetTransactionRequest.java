package com.example.moneylens.application.model.request;

import com.example.moneylens.application.common.constant.Type;
import com.example.moneylens.application.model.common.SortedPageRequest;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionRequest extends SortedPageRequest {
    private Instant fromDate;
    private Instant toDate;
    private String categoryCode;
    private Type type;
}
