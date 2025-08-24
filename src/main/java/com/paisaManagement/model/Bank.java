package com.paisaManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String accountHolderName;
    private String bankName;
    private String accountNumber;
    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;
//    private float amount;
    private boolean phonePay;
    private boolean googlePay;
    private boolean paytm;
    private boolean bhimUpi;
    private boolean isActive;
    private long mobileNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy ="bank" , cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<BankExpenses> bankExpenses=new ArrayList<>();


}
