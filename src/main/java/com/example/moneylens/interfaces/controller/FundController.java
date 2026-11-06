package com.example.moneylens.interfaces.controller;

import com.example.moneylens.application.model.common.BaseResponse;
import com.example.moneylens.application.model.common.PageResponse;
import com.example.moneylens.application.model.request.fund.CreateFundRequest;
import com.example.moneylens.application.model.request.fund.GetFundRequest;
import com.example.moneylens.application.model.response.fund.FundDetailResponse;
import com.example.moneylens.application.model.response.fund.FundResponse;
import com.example.moneylens.application.service.FundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/funds")
@RequiredArgsConstructor
public class FundController {
    private final FundService fundService;

    @GetMapping
    public ResponseEntity<BaseResponse<PageResponse<FundResponse>>> getAllFund(@ParameterObject @Valid GetFundRequest request) {
        PageResponse<FundResponse> response = fundService.getAll(request);
        return ResponseEntity.ok(BaseResponse.success(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<FundDetailResponse>> getDetailFund(@PathVariable String id) {
        FundDetailResponse response = fundService.getDetail(id);
        return ResponseEntity.ok(BaseResponse.success(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createFund(@Valid @RequestBody CreateFundRequest request) {
        fundService.createFund(request);
        return ResponseEntity.ok(BaseResponse.success());
    }
}
