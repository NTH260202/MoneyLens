package com.example.moneylens.interfaces.controller;

import com.example.moneylens.application.model.common.SortedPageRequest;
import com.example.moneylens.application.model.common.PageResponse;
import com.example.moneylens.application.model.request.CreateTransactionRequest;
import com.example.moneylens.application.model.response.TransactionResponse;
import com.example.moneylens.application.model.common.BaseResponse;
import com.example.moneylens.application.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<PageResponse<TransactionResponse>> getAllTransactions(
            @ParameterObject SortedPageRequest sortedPageRequest) {
        PageResponse<TransactionResponse> response = transactionService.getAll(sortedPageRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{transactionCode}")
    public ResponseEntity<BaseResponse<TransactionResponse>> getDetailTransaction(
            @PathVariable String transactionCode) {
        TransactionResponse response = transactionService.getDetail(transactionCode);
        return ResponseEntity.ok(BaseResponse.success(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createTransaction(@RequestBody CreateTransactionRequest request) {
        transactionService.insertTransaction(request);
        return ResponseEntity.ok(BaseResponse.success());
    }
}
