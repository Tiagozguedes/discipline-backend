package com.discipline.repository;

import com.discipline.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

    @Query("SELECT COALESCE(SUM(i.amountInvested), 0) FROM Investment i")
    BigDecimal sumTotalInvested();

    @Query("SELECT COALESCE(SUM(i.currentValue), 0) FROM Investment i")
    BigDecimal sumTotalCurrentValue();

    @Query("SELECT i.type, SUM(i.currentValue) FROM Investment i GROUP BY i.type")
    List<Object[]> sumAllocationByType();
}
