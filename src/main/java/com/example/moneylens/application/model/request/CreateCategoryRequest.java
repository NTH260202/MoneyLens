package com.example.moneylens.application.model.request;

import com.example.moneylens.application.common.constant.Type;
import lombok.Data;

@Data
public class CreateCategoryRequest {
    private String code;

    private Type type;

    private String icon;

    private String color;
}
