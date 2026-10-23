package com.example.moneylens.application.service;

import com.example.moneylens.application.model.common.SortedPageRequest;
import com.example.moneylens.application.model.common.PageResponse;
import com.example.moneylens.application.model.request.CreateTransactionRequest;
import com.example.moneylens.application.model.response.TransactionResponse;

public interface TransactionService {
    void insertTransaction(CreateTransactionRequest request);

    PageResponse<TransactionResponse> getAllTransactions(SortedPageRequest sortedPageRequest);
}
