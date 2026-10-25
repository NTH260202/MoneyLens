package com.example.moneylens.application.model.request;

import com.example.moneylens.application.common.constant.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    @NotBlank
    private String code;

    @NotBlank
    private Type type;

    @NotBlank
    private String icon;

    @NotBlank
    private String color;
}
