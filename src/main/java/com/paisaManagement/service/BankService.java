package com.paisaManagement.service;

import com.paisaManagement.model.Bank;
import com.paisaManagement.request.BankRequest;

public interface BankService {
    Bank addBank(BankRequest bankRequest);
}
