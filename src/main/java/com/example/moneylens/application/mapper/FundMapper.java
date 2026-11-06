package com.example.moneylens.application.mapper;

import com.example.moneylens.application.model.request.fund.CreateFundRequest;
import com.example.moneylens.application.model.response.fund.FundResponse;
import com.example.moneylens.domain.entity.Fund;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FundMapper {
    Fund toEntity(CreateFundRequest request);

    List<FundResponse> toResponse(List<Fund> content);
}
