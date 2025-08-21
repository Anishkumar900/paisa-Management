package com.paisaManagement.serviceImpl;

import com.paisaManagement.exception.UserNotFoundException;
import com.paisaManagement.model.Bank;
import com.paisaManagement.model.User;
import com.paisaManagement.repository.BankRepository;
import com.paisaManagement.repository.UserRepository;
import com.paisaManagement.request.BankRequest;
import com.paisaManagement.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final UserRepository userRepository;


    @Override
    public Bank addBank(BankRequest bankRequest) {
        User user = userRepository.findByEmail(bankRequest.getEmail());
        if(user==null){
            throw new UserNotFoundException("User not found");
        }
        try{
            Bank bank=new Bank();
            bank.setBankName(bankRequest.getBankName());
            bank.setAccountHolderName(bankRequest.getAccountHolderName());
            bank.setAccountNumber(bankRequest.getAccountNumber());
            bank.setAmount(Float.parseFloat(bankRequest.getAmount()));
            bank.setPhonePay(bankRequest.isPhonePay());
            bank.setGooglePay(bankRequest.isGooglePay());
            bank.setPaytm(bankRequest.isPaytm());
            bank.setBhimUpi(bankRequest.isBhimUpi());
            bank.setMobileNumber(Long.parseLong(bankRequest.getMobileNumber()));
            bank.setActive(bankRequest.isActive());
            bank.setUser(user);
            return bankRepository.save(bank);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


    }
}
