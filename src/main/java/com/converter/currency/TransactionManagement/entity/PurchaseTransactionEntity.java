package com.converter.currency.TransactionManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Purchase_transaction")
public class PurchaseTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier", nullable = false)
    private Integer identifier;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "transactionDate", nullable = false)
    private LocalDate transactionDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

}
