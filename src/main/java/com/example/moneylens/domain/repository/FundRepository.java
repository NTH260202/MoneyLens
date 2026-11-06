package com.example.moneylens.domain.repository;

import com.example.moneylens.domain.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends JpaRepository<Fund, String>, JpaSpecificationExecutor<Fund> {
}
