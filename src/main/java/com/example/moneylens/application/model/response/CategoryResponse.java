package com.example.moneylens.application.model.response;

import com.example.moneylens.application.common.constant.Type;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String code;
    private String name;
    private Type type;
    private String icon;
    private String color;
}
