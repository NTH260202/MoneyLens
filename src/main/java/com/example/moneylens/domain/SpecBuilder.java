package com.example.moneylens.domain;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/** Xây query động bằng JPA metamodel: điều kiện nào có giá trị null / rỗng sẽ tự bị bỏ qua. */
public final class SpecBuilder<T> {

    private final List<Specification<T>> specs = new ArrayList<>();

    private SpecBuilder() {
    }

    public static <T> SpecBuilder<T> create() {
        return new SpecBuilder<>();
    }

    /** field = value. Ví dụ: eq(Transaction_.type, request.getType()) */
    public <Y> SpecBuilder<T> eq(SingularAttribute<? super T, Y> attr, Y value) {
        if (hasValue(value)) {
            specs.add((root, query, cb) -> cb.equal(root.get(attr), value));
        }
        return this;
    }

    /** field lồng nhau = value. Ví dụ: eqPath(userId, Transaction_.user, User_.id) */
    public SpecBuilder<T> eqPath(Object value, SingularAttribute<?, ?>... attrs) {
        if (hasValue(value)) {
            specs.add((root, query, cb) -> cb.equal(path(root, attrs), value));
        }
        return this;
    }

    /** field IN (values). Ví dụ: in(Transaction_.type, List.of(INCOME, EXPENSE)) */
    public <Y> SpecBuilder<T> in(SingularAttribute<? super T, Y> attr, Collection<? extends Y> values) {
        if (hasValue(values)) {
            specs.add((root, query, cb) -> root.get(attr).in(values));
        }
        return this;
    }

    /** field lồng nhau IN (values). Ví dụ: inPath(categoryIds, Transaction_.category, Category_.id) */
    public SpecBuilder<T> inPath(Collection<?> values, SingularAttribute<?, ?>... attrs) {
        if (hasValue(values)) {
            specs.add((root, query, cb) -> path(root, attrs).in(values));
        }
        return this;
    }

    /** Tìm keyword (không phân biệt hoa thường) trên một hoặc nhiều field String, nối bằng OR. */
    @SafeVarargs
    public final SpecBuilder<T> like(String keyword, SingularAttribute<? super T, String>... attrs) {
        if (hasValue(keyword)) {
            String pattern = toLikePattern(keyword);
            specs.add((root, query, cb) -> cb.or(
                    Arrays.stream(attrs)
                            .map(a -> cb.like(cb.lower(root.get(a)), pattern, '\\'))
                            .toArray(Predicate[]::new)));
        }
        return this;
    }

    /** Tìm keyword trên field String lồng nhau. Ví dụ: likePath(kw, Transaction_.category, Category_.code) */
    public SpecBuilder<T> likePath(String keyword, SingularAttribute<?, ?>... attrs) {
        if (hasValue(keyword)) {
            String pattern = toLikePattern(keyword);
            specs.add((root, query, cb) ->
                    cb.like(cb.lower(SpecBuilder.<String>path(root, attrs)), pattern, '\\'));
        }
        return this;
    }

    /** field >= value. Ví dụ: gte(Transaction_.transactionDate, request.getFromDate()) */
    public <Y extends Comparable<? super Y>> SpecBuilder<T> gte(SingularAttribute<? super T, Y> attr, Y value) {
        if (value != null) {
            specs.add((root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attr), value));
        }
        return this;
    }

    /** field <= value. */
    public <Y extends Comparable<? super Y>> SpecBuilder<T> lte(SingularAttribute<? super T, Y> attr, Y value) {
        if (value != null) {
            specs.add((root, query, cb) -> cb.lessThanOrEqualTo(root.get(attr), value));
        }
        return this;
    }

    /** field < value. */
    public <Y extends Comparable<? super Y>> SpecBuilder<T> lt(SingularAttribute<? super T, Y> attr, Y value) {
        if (value != null) {
            specs.add((root, query, cb) -> cb.lessThan(root.get(attr), value));
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

    // ---------------------------------------------------------------------

    /** Đi theo chuỗi metamodel, ví dụ Transaction_.category, Category_.id. */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <Y> Path<Y> path(Root<?> root, SingularAttribute<?, ?>... attrs) {
        Path p = root;
        for (SingularAttribute a : attrs) {
            p = p.get(a);
        }
        return (Path<Y>) p;
    }

    private static String toLikePattern(String keyword) {
        return "%" + escape(keyword.trim().toLowerCase()) + "%";
    }

    /** Escape ký tự đặc biệt của LIKE để keyword "50%" không bị hiểu thành wildcard. */
    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
    }

    private static boolean hasValue(Object value) {
        if (value == null) return false;
        if (value instanceof String s) return !s.isBlank();
        if (value instanceof Collection<?> c) return !c.isEmpty();
        return true;
    }
}