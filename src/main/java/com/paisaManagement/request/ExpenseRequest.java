package com.paisaManagement.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequest {
    private String email;
    private String expenseDate;
    private String reason;
    private String amount;
    private String requirement;
    private String savedAmount;
    private String bank;
    private String expediterType;
    private String returnDate;
    private String returnName;
    private String returnStatus;
}
