package com.example.moneylens.application.service.impl;

import com.example.moneylens.application.common.constant.FundRole;
import com.example.moneylens.application.common.constant.FundStatus;
import com.example.moneylens.application.common.constant.MoneyLensCode;
import com.example.moneylens.application.common.exception.MoneyLensException;
import com.example.moneylens.application.common.utils.AuthUtils;
import com.example.moneylens.application.mapper.FundMapper;
import com.example.moneylens.application.model.common.PageResponse;
import com.example.moneylens.application.model.request.fund.CreateFundRequest;
import com.example.moneylens.application.model.request.fund.GetFundRequest;
import com.example.moneylens.application.model.response.fund.FundDetailResponse;
import com.example.moneylens.application.model.response.fund.FundResponse;
import com.example.moneylens.application.service.FundService;
import com.example.moneylens.domain.SpecBuilder;
import com.example.moneylens.domain.entity.*;
import com.example.moneylens.domain.repository.FundRepository;
import com.example.moneylens.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FundServiceImpl implements FundService {
    private final FundRepository fundRepository;
    private final FundMapper fundMapper;
    private final UserRepository userRepository;

    @Override
    public void createFund(CreateFundRequest request) {
        if (request.getMembers().stream().anyMatch(member -> member.getRole().equals(FundRole.OWNER))) {
            throw new MoneyLensException(MoneyLensCode.FUND_OWNER_ALREADY_EXISTS);
        }

        String ownerId = AuthUtils.requireUserId();
        User owner = userRepository.findById(ownerId).orElseThrow();

        Fund fund = fundMapper.toEntity(request);
        fund.setId(UUID.randomUUID().toString());
        fund.setStatus(FundStatus.OPEN);

        List<FundMember> fundMembers = new ArrayList<>();

        fundMembers.add(FundMember.builder()
                .fund(fund)
                .user(owner)
                .role(FundRole.OWNER)
                .build()
        );

        for (CreateFundRequest.FundMember member: request.getMembers()) {
            FundMember fundMember = new FundMember();
            if (!member.getRole().equals(FundRole.ANONYMOUS)) {
                User loginUser = userRepository.findUserByUsername(member.getMemberUsername()).orElseThrow();

                fundMember.setUser(loginUser);
                fundMember.setRole(member.getRole());
            } else {
                fundMember.setMemberName(member.getMemberUsername());
                fundMember.setRole(member.getRole());
            }

            fundMembers.add(fundMember);
        }

        fund.setMembers(fundMembers);
        fundRepository.save(fund);
    }

    @Override
    public FundDetailResponse getDetail(String id) {
        return null;
    }

    @Override
    public PageResponse<FundResponse> getAll(GetFundRequest request) {
        Pageable pageable = PageRequest.of(request.getPageNo() - 1, request.getPageSize());

        Specification<Fund> spec = SpecBuilder.<Fund>create()
                .eq(Fund_.createdBy, AuthUtils.requireUserId())
                .eq(Fund_.status, request.getStatus())
                .build();

        Page<Fund> fundPage = fundRepository.findAll(spec, pageable);

        List<FundResponse> pageContent = fundMapper.toResponse(fundPage.getContent());

        return PageResponse.success(pageContent, fundPage.getTotalPages(), fundPage.getNumberOfElements());
    }
}
