package com.example.moneylens.interfaces.controller;

import com.example.moneylens.application.model.common.BaseResponse;
import com.example.moneylens.application.model.request.CreateCategoryRequest;
import com.example.moneylens.application.model.response.CategoryResponse;
import com.example.moneylens.application.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryResponse>>> getAllCategories() {
        List<CategoryResponse> response = categoryService.getAll();
        return ResponseEntity.ok(BaseResponse.success(response));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
        categoryService.create(request);
        return ResponseEntity.ok(BaseResponse.success());
    }
}
