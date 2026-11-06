package com.example.moneylens.application.model.request.fund;

import com.example.moneylens.application.common.constant.FundStatus;
import com.example.moneylens.application.model.common.SortedPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetFundRequest extends SortedPageRequest {
    private FundStatus status;
}
