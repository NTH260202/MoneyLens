package com.example.moneylens.application.model.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortedPageRequest {
    @NotNull
    @Min(value = 1, message = "Page number must be at least 1")
    private int pageNo;

    @NotNull
    @Min(value = 1, message = "Page size must be at least 1")
    private int pageSize;

    private String orderBy;
    private String orderDirection;
}
