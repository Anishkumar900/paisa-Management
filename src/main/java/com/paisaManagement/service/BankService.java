package com.paisaManagement.service;

import com.paisaManagement.model.Bank;
import com.paisaManagement.request.BankRequest;

import java.util.List;

public interface BankService {
    Bank addBank(BankRequest bankRequest) ;
    List<Bank> getAllBank(String token);
    Bank editBank(BankRequest bankRequest);
    void deleteBank(Long id);
    float debitAmount(Long id,String amount);
    float creditAmount(Long id,String amount);
    Bank getBankDetailsById(Long id);
}
