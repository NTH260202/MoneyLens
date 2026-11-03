package com.example.moneylens.application.service;

import com.example.moneylens.application.model.common.PageResponse;
import com.example.moneylens.application.model.request.CreateFundRequest;
import com.example.moneylens.application.model.request.GetFundRequest;
import com.example.moneylens.application.model.response.FundDetailResponse;
import com.example.moneylens.application.model.response.FundResponse;

public interface FundService {
    void createFund(CreateFundRequest request);

    FundDetailResponse getDetail(String id);

    PageResponse<FundResponse> getAll(GetFundRequest request);
}
