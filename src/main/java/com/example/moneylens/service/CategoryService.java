package com.example.moneylens.service;

import com.example.moneylens.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();
}
