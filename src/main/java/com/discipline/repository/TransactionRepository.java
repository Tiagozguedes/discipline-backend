package com.discipline.repository;

import com.discipline.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByDateBetweenOrderByDateDesc(LocalDate start, LocalDate end);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.type = :type AND t.date BETWEEN :start AND :end")
    BigDecimal sumByTypeAndDateBetween(@Param("type") String type, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.type = :type AND t.date BETWEEN :start AND :end GROUP BY t.category")
    List<Object[]> sumByCategoryAndTypeAndDateBetween(@Param("type") String type, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
