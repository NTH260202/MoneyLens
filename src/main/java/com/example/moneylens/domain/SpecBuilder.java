package com.example.moneylens.domain;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class SpecBuilder<T> {

    private final List<Specification<T>> specs = new ArrayList<>();

    private SpecBuilder() {
    }

    public static <T> SpecBuilder<T> create() {
        return new SpecBuilder<>();
    }

    /** field = value. Hỗ trợ đường dẫn lồng nhau, ví dụ "category.id". */
    public SpecBuilder<T> eq(String field, Object value) {
        if (hasValue(value)) {
            specs.add((root, query, cb) -> cb.equal(path(root, field), value));
        }
        return this;
    }

    /** field IN (values). */
    public SpecBuilder<T> in(String field, Collection<?> values) {
        if (hasValue(values)) {
            specs.add((root, query, cb) -> path(root, field).in(values));
        }
        return this;
    }

    /** Tìm keyword (không phân biệt hoa thường) trên một hoặc nhiều field, nối bằng OR. */
    public SpecBuilder<T> like(String keyword, String... fields) {
        if (hasValue(keyword)) {
            String pattern = "%" + escape(keyword.trim().toLowerCase()) + "%";
            specs.add((root, query, cb) -> cb.or(
                    java.util.Arrays.stream(fields)
                            .map(f -> cb.like(cb.lower(SpecBuilder.<String>path(root, f)), pattern, '\\'))
                            .toArray(Predicate[]::new)));
        }
        return this;
    }

    /** field >= value. */
    public <Y extends Comparable<? super Y>> SpecBuilder<T> gte(String field, Y value) {
        if (value != null) {
            specs.add((root, query, cb) -> cb.greaterThanOrEqualTo(SpecBuilder.<Y>path(root, field), value));
        }
        return this;
    }

    /** field < value. */
    public <Y extends Comparable<? super Y>> SpecBuilder<T> lt(String field, Y value) {
        if (value != null) {
            specs.add((root, query, cb) -> cb.lessThan(SpecBuilder.<Y>path(root, field), value));
        }
        return this;
    }

    /** Điều kiện tuỳ ý khi các hàm trên không đủ. */
    public SpecBuilder<T> and(boolean condition, Specification<T> spec) {
        if (condition) {
            specs.add(spec);
        }
        return this;
    }

    public Specification<T> build() {
        return (root, query, cb) -> cb.and(specs.stream()
                .map(s -> s.toPredicate(root, query, cb))
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new));
    }

    // ------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    private static <Y> Path<Y> path(Root<?> root, String dotted) {
        Path<?> p = root;
        for (String part : dotted.split("\\.")) {
            p = p.get(part);
        }
        return (Path<Y>) p;
    }

    private static boolean hasValue(Object value) {
        if (value == null) return false;
        if (value instanceof String s) return !s.isBlank();
        if (value instanceof Collection<?> c) return !c.isEmpty();
        return true;
    }

    /** Escape ký tự đặc biệt của LIKE để keyword "50%" không bị hiểu thành wildcard. */
    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
    }
}
