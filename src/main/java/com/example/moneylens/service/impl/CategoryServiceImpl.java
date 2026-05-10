package com.example.moneylens.service.impl;

import com.example.moneylens.mapper.CategoryMapper;
import com.example.moneylens.model.entity.Category;
import com.example.moneylens.model.response.CategoryResponse;
import com.example.moneylens.repository.CategoryRepository;
import com.example.moneylens.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
