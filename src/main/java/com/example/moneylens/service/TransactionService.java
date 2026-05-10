package com.example.moneylens.service;

import com.example.moneylens.model.common.SortedPageRequest;
import com.example.moneylens.model.common.PageResponse;
import com.example.moneylens.model.request.CreateTransactionRequest;
import com.example.moneylens.model.response.TransactionResponse;

public interface TransactionService {
    void insertTransaction(CreateTransactionRequest request);

    PageResponse<TransactionResponse> getAllTransactions(SortedPageRequest sortedPageRequest);
}
