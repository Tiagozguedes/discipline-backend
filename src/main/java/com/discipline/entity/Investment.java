package com.discipline.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "investments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // stock, crypto, fixed_income, fund, real_estate, other

    @Column(nullable = false)
    private BigDecimal amountInvested;

    @Column(nullable = false)
    private BigDecimal currentValue;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    private String notes;
}
