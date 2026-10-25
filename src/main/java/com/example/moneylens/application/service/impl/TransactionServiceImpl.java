package com.example.moneylens.application.service.impl;

import com.example.moneylens.application.common.constant.MoneyLensCode;
import com.example.moneylens.application.common.exception.MoneyLensException;
import com.example.moneylens.application.mapper.TransactionMapper;
import com.example.moneylens.application.model.common.PageResponse;
import com.example.moneylens.application.model.common.SortedPageRequest;
import com.example.moneylens.domain.entity.Category;
import com.example.moneylens.domain.entity.Transaction;
import com.example.moneylens.application.model.request.CreateTransactionRequest;
import com.example.moneylens.application.model.response.TransactionResponse;
import com.example.moneylens.domain.repository.CategoryRepository;
import com.example.moneylens.domain.repository.TransactionRepository;
import com.example.moneylens.application.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final CategoryRepository categoryRepository;
    private static final String DEFAULT_CATEGORY_CODE = "OTHERS";

    @Override
    @Transactional
    public void insertTransaction(CreateTransactionRequest request) {
        if (StringUtils.isBlank(request.getCategoryCode())) {
            request.setCategoryCode(DEFAULT_CATEGORY_CODE);
        }

        Transaction transaction = transactionMapper.toEntity(request);
        transaction.setTransactionCode(UUID.randomUUID().toString());

        Category category = categoryRepository
                .findById(request.getCategoryCode())
                .orElseThrow(() ->
                        new MoneyLensException(MoneyLensCode.CATEGORY_NOT_FOUND)
                );

        transaction.setCategory(category);

        transactionRepository.save(transaction);
    }

    @Override
    public PageResponse<TransactionResponse> getAll(SortedPageRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNo() - 1, request.getPageSize());

        Page<Transaction> transactionPage = transactionRepository.findAll(pageable);

        List<TransactionResponse> pageContent = transactionMapper.toResponse(transactionPage.getContent());

        return PageResponse.success(pageContent, transactionPage.getTotalPages(), transactionPage.getNumberOfElements());
    }

    @Override
    public TransactionResponse getDetail(String transactionCode) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionCode);

        if (transaction.isPresent()) {
            return transactionMapper.toResponse(transaction.get());
        }

        throw new MoneyLensException(MoneyLensCode.TRANSACTION_NOT_FOUND);
    }
}
