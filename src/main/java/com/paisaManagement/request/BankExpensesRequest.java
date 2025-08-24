package com.paisaManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankExpensesRequest {
    private String bank_id;
    private String date;
    private String amount;
    private String amountDebit;
    private String reason;
}
