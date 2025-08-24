package com.paisaManagement.service;

import com.paisaManagement.model.BankExpenses;
import com.paisaManagement.request.BankExpensesRequest;

import java.util.List;

public interface BankExpensesService {
    BankExpenses addBankExpenses(BankExpensesRequest bankExpensesRequest);
    List<BankExpenses> getBankExpensesByBankId(Long id);
    void deleteBankExpensesById(Long id);
}
