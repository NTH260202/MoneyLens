package com.example.moneylens.application.service.impl;

import com.example.moneylens.application.common.constant.MoneyLensCode;
import com.example.moneylens.application.common.exception.MoneyLensException;
import com.example.moneylens.application.common.utils.AuthUtils;
import com.example.moneylens.application.mapper.CategoryMapper;
import com.example.moneylens.application.model.request.CreateCategoryRequest;
import com.example.moneylens.application.model.response.CategoryResponse;
import com.example.moneylens.application.service.CategoryService;
import com.example.moneylens.domain.entity.Category;
import com.example.moneylens.domain.repository.CategoryRepository;
import com.example.moneylens.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        return categoryMapper.toResponse(categories);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CreateCategoryRequest request) {
        String userId = AuthUtils.currentUserId().orElse(null);

        userRepository.lockById(userId);

        long count = categoryRepository.countCategoryByCreatedBy(userId);
        if (count >= 20) {
            throw new MoneyLensException(MoneyLensCode.CATEGORY_LIMIT_EXCEEDED);
        }

        Category category = categoryMapper.toEntity(request);
        category.setCode(UUID.randomUUID().toString());

        categoryRepository.save(category);
    }
}
