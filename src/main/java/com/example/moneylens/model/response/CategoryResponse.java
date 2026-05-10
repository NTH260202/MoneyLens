package com.example.moneylens.model.response;

import com.example.moneylens.common.constant.Type;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String code;
    private String nameEn;
    private String nameVn;
    private Type type;
}
