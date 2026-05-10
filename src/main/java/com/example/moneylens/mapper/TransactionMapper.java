package com.example.moneylens.mapper;

import com.example.moneylens.model.entity.Transaction;
import com.example.moneylens.model.request.CreateTransactionRequest;
import com.example.moneylens.model.response.TransactionResponse;
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
