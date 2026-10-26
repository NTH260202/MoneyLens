package com.example.moneylens.application.service.impl;

import com.example.moneylens.application.mapper.CategoryMapper;
import com.example.moneylens.application.model.request.CreateCategoryRequest;
import com.example.moneylens.domain.entity.Category;
import com.example.moneylens.application.model.response.CategoryResponse;
import com.example.moneylens.domain.repository.CategoryRepository;
import com.example.moneylens.application.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        return categoryMapper.toResponse(categories);
    }

    @Override
    public void create(CreateCategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        category.setCode(UUID.randomUUID().toString());

        categoryRepository.save(category);
    }
}
