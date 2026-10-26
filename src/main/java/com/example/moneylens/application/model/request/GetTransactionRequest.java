package com.example.moneylens.application.model.request;

import com.example.moneylens.application.model.common.SortedPageRequest;
import com.example.moneylens.domain.entity.Transaction;
import jakarta.persistence.criteria.Predicate;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionRequest extends SortedPageRequest {
    private Instant fromDate;
    private Instant toDate;

    public static Specification<Transaction> filter(String userId,
                                                    String keyword,
                                                    String type,
                                                    String categoryId,
                                                    Instant fromDate,
                                                    Instant toDate) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Luôn lọc theo user đang đăng nhập
            predicates.add(cb.equal(root.get("user").get("id"), userId));

            if (fromDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("transactionDate"), fromDate));
            }
            if (toDate != null) {
                predicates.add(cb.lessThan(root.get("transactionDate"), toDate));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
