package com.example.moneylens.model.common;

import com.example.moneylens.common.constant.AppCode;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> extends BaseResponse<List<T>> {
    private int totalPages;
    private int totalElements;

    public static <T> PageResponse<T> success(
            List<T> data,
            int totalPages,
            int totalElements
    ) {

        return PageResponse.<T>builder()
                .code(AppCode.SUCCESS)
                .message("Success")
                .data(data)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .build();
    }
}
