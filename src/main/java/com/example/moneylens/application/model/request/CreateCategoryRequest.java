package com.example.moneylens.application.model.request;

import com.example.moneylens.application.common.constant.Type;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @NotNull
    private Type type;

    @NotBlank
    private String icon;

    @NotBlank
    private String name;

    @NotBlank
    private String color;
}
