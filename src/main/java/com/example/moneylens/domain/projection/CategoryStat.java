package com.example.moneylens.domain.projection;

import java.math.BigDecimal;

public interface CategoryStat {
    String getCode();
    String getIcon();
    String getColor();
    BigDecimal getTotal();
    Long getCount();
}
