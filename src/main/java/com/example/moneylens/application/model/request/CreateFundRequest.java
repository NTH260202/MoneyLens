package com.example.moneylens.application.model.request;

import com.example.moneylens.application.common.constant.FundRole;
import com.example.moneylens.application.common.constant.FundType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
public class CreateFundRequest {
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private FundType type;

    @NotEmpty
    private List<FundMember> members;

    private Instant from;

    private Instant to;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FundMember {
        private String memberUsername;
        private FundRole role;
    }
}
