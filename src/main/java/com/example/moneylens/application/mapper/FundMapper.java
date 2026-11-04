package com.example.moneylens.application.mapper;

import com.example.moneylens.application.model.request.CreateFundRequest;
import com.example.moneylens.domain.entity.Fund;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FundMapper {
    Fund toEntity(CreateFundRequest request);
}
