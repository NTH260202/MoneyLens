package com.example.moneylens.application.service;

import com.example.moneylens.application.model.request.CreateCategoryRequest;
import com.example.moneylens.application.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();

    void create(CreateCategoryRequest request);
}
