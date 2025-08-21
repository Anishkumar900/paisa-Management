package com.paisaManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankRequest {

    private String email;
    private String accountHolderName;
    private String bankName;
    private String accountNumber;
    private String amount;
    private boolean phonePay;
    private boolean googlePay;
    private boolean paytm;
    private boolean bhimUpi;
    private boolean isActive;
    private String mobileNumber;
}
