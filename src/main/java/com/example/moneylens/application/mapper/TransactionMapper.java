package com.example.moneylens.application.mapper;

import com.example.moneylens.domain.entity.Transaction;
import com.example.moneylens.application.model.request.CreateTransactionRequest;
import com.example.moneylens.application.model.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface TransactionMapper {
    List<TransactionResponse> toResponse(List<Transaction> entity);

    @Mapping(target = "categoryResponse", source = "category")
    TransactionResponse toResponse(Transaction entity);

    @Mapping(target = "category.code", source = "categoryCode")
    Transaction toEntity(CreateTransactionRequest request);
}
