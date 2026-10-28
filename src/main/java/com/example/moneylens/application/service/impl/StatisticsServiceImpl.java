package com.example.moneylens.application.service.impl;

import com.example.moneylens.application.common.constant.Type;
import com.example.moneylens.application.common.utils.AuthUtils;
import com.example.moneylens.application.model.response.CategoryBreakdownResponse;
import com.example.moneylens.application.service.StatisticsService;
import com.example.moneylens.domain.projection.CategoryStat;
import com.example.moneylens.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticsService {
    private static final ZoneId DEFAULT_ZONE = ZoneId.of("Asia/Ho_Chi_Minh");
    private static final Duration MAX_RANGE = Duration.ofDays(366);

    private final TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public CategoryBreakdownResponse byCategory(Type type, Instant from, Instant to) {
        Range range = resolveRange(from, to);
        List<CategoryStat> stats = transactionRepository.sumByCategory(
                AuthUtils.requireUserId(), type, range.from(), range.to());

        BigDecimal total = stats.stream()
                .map(CategoryStat::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<CategoryBreakdownResponse.Item> items = stats.stream()
                .map(s -> new CategoryBreakdownResponse.Item(
                        s.getCode(), s.getIcon(), s.getColor(),
                        s.getTotal(), s.getCount(), percentage(s.getTotal(), total)))
                .toList();

        return new CategoryBreakdownResponse(type, range.from(), range.to(), total, items);
    }

    // ---------------------------------------------------------------------

    private record Range(Instant from, Instant to) {
    }

    /**
     * Khoảng [from, to). Nếu thiếu thì mặc định là tháng hiện tại theo giờ Việt Nam:
     * from = 00:00 ngày 1 tháng này, to = 00:00 ngày 1 tháng sau.
     */
    private static Range resolveRange(Instant from, Instant to) {
        ZonedDateTime startOfMonth = LocalDate.now(DEFAULT_ZONE)
                .withDayOfMonth(1)
                .atStartOfDay(DEFAULT_ZONE);

        Instant f = from != null ? from : startOfMonth.toInstant();
        Instant t = to != null ? to : startOfMonth.plusMonths(1).toInstant();

        if (!f.isBefore(t)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "from phải nhỏ hơn to");
        }
        if (Duration.between(f, t).compareTo(MAX_RANGE) > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Khoảng thời gian tối đa 1 năm");
        }
        return new Range(f, t);
    }

    private static BigDecimal percentage(BigDecimal part, BigDecimal total) {
        if (total.signum() == 0) {
            return BigDecimal.ZERO;
        }
        return part.multiply(BigDecimal.valueOf(100)).divide(total, 2, RoundingMode.HALF_UP);
    }
}
