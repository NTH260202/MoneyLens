package com.example.moneylens.application.service;

import com.example.moneylens.application.model.common.PageResponse;
import com.example.moneylens.application.model.request.fund.CreateFundRequest;
import com.example.moneylens.application.model.request.fund.GetFundRequest;
import com.example.moneylens.application.model.response.fund.FundDetailResponse;
import com.example.moneylens.application.model.response.fund.FundResponse;

public interface FundService {
    void createFund(CreateFundRequest request);

    FundDetailResponse getDetail(String id);

    PageResponse<FundResponse> getAll(GetFundRequest request);
}
