package com.example.moneylens.service.impl;

import com.example.moneylens.mapper.TransactionMapper;
import com.example.moneylens.model.common.PageResponse;
import com.example.moneylens.model.common.SortedPageRequest;
import com.example.moneylens.model.entity.Transaction;
import com.example.moneylens.model.request.CreateTransactionRequest;
import com.example.moneylens.model.response.TransactionResponse;
import com.example.moneylens.repository.TransactionRepository;
import com.example.moneylens.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public void insertTransaction(CreateTransactionRequest request) {

        Transaction transaction = transactionMapper.toEntity(request);
        transaction.setTransactionCode(UUID.randomUUID().toString());

        transactionRepository.save(transaction);
    }

    @Override
    public PageResponse<TransactionResponse> getAllTransactions(SortedPageRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNo() - 1, request.getPageSize());

        Page<Transaction> transactionPage = transactionRepository.findAll(pageable);

        List<TransactionResponse> pageContent = transactionMapper.toResponse(transactionPage.getContent());

        return PageResponse.success(pageContent, transactionPage.getTotalPages(), transactionPage.getNumberOfElements());
    }
}
