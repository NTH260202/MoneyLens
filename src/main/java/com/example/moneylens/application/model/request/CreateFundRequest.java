package com.example.moneylens.application.model.request;

import com.example.moneylens.application.common.constant.FundRole;
import com.example.moneylens.application.common.constant.FundType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
public class CreateFundRequest {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private FundType fundType;

    @NotEmpty
    private List<FundMember> members;

    private Instant fromDate;

    private Instant toDate;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FundMember {
        private String memberUsername;

        @NotNull
        private FundRole role;
    }
}
