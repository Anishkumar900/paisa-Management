package com.paisaManagement.serviceImpl;

import com.paisaManagement.exception.UserNotFoundException;
import com.paisaManagement.model.Bank;
import com.paisaManagement.model.BankExpenses;
import com.paisaManagement.repository.BankExpensesRepository;
import com.paisaManagement.repository.BankRepository;
import com.paisaManagement.request.BankExpensesRequest;
import com.paisaManagement.service.BankExpensesService;
import com.paisaManagement.util.UpdateNameEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class BankExpensesServiceImpl implements BankExpensesService {
    private final BankRepository bankRepository;
    private final BankExpensesRepository bankExpensesRepository;
    @Override
    public BankExpenses addBankExpenses(BankExpensesRequest bankExpensesRequest) {
        Bank bank=bankRepository.findById(Long.valueOf(bankExpensesRequest.getBank_id())).orElseThrow(()->new UserNotFoundException("Bank not found"));
        BigDecimal balance = bank.getAmount();
        BankExpenses bankExpenses=new BankExpenses();
        if (bankExpensesRequest.getAmount() != null && !bankExpensesRequest.getAmount().trim().isEmpty()) {
            bankExpenses.setAmount(new BigDecimal(bankExpensesRequest.getAmount()));
            bank.setAmount(balance.subtract(new BigDecimal(bankExpensesRequest.getAmount())));
        } else {
            bankExpenses.setAmount(BigDecimal.ZERO);
        }

        bankExpenses.setAmountDebit(bankExpensesRequest.getAmountDebit());
        bankExpenses.setDate(bankExpensesRequest.getDate());
        bankExpenses.setReason(UpdateNameEmail.updateName(bankExpensesRequest.getReason()));
        bankExpenses.setBank(bank);

        bankRepository.save(bank);
        return bankExpensesRepository.save(bankExpenses);
    }

    @Override
    public List<BankExpenses> getBankExpensesByBankId(Long id) {
        return bankExpensesRepository.findAllByBankId(id);

    }

    @Override
    public void deleteBankExpensesById(Long id) {
        BankExpenses bankExpenses=bankExpensesRepository.findById(id).orElseThrow(()->new UserNotFoundException("Expenses not found."));
        Bank bank = bankExpenses.getBank();
        bank.setAmount(bank.getAmount().add(bankExpenses.getAmount()));
        bankRepository.save(bank);
        bankExpensesRepository.deleteById(id);
    }
}
