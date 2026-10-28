package com.example.moneylens.domain.repository;

import com.example.moneylens.application.common.constant.Type;
import com.example.moneylens.domain.entity.Transaction;
import com.example.moneylens.domain.projection.CategoryStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<Transaction> {

    @Query("""
        select c.code as code, c.icon as icon, c.color as color,
               coalesce(sum(t.amount), 0) as total, count(t) as count
        from Transaction t join t.category c
        where t.createdBy = :userId
          and t.type = :type
          and t.transactionDate >= :from and t.transactionDate < :to
        group by c.code, c.icon, c.color
        order by sum(t.amount) desc
        """)
    List<CategoryStat> sumByCategory(@Param("userId") String userId,
                                     @Param("type") Type type,
                                     @Param("from") Instant from,
                                     @Param("to") Instant to);
}
