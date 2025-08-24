package com.paisaManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankExpenses {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String date;
    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;
    private String amountDebit;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    @JsonBackReference
    private Bank bank;

}
