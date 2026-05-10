package com.example.moneylens.controller;

import com.example.moneylens.model.common.SortedPageRequest;
import com.example.moneylens.model.common.PageResponse;
import com.example.moneylens.model.request.CreateTransactionRequest;
import com.example.moneylens.model.response.TransactionResponse;
import com.example.moneylens.model.common.BaseResponse;
import com.example.moneylens.service.TransactionService;
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
        PageResponse<TransactionResponse> response = transactionService.getAllTransactions(sortedPageRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createTransaction(@RequestBody CreateTransactionRequest request) {
        transactionService.insertTransaction(request);
        return ResponseEntity.ok(BaseResponse.success());
    }
}
