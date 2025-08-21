package com.paisaManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String accountHolderName;
    private String bankName;
    private String accountNumber;
    private float amount;
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

}
