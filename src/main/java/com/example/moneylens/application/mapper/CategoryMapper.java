package com.example.moneylens.application.mapper;

import com.example.moneylens.application.model.request.CreateCategoryRequest;
import com.example.moneylens.domain.entity.Category;
import com.example.moneylens.application.model.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponse(List<Category> categories);

    @Mapping(target = "code", ignore = true)
    Category toEntity(CreateCategoryRequest request);
}
