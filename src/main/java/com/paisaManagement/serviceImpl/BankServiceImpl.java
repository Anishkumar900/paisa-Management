//package com.paisaManagement.serviceImpl;
//
//import com.paisaManagement.configuration.JWTService;
//import com.paisaManagement.exception.BankAlreadyExistsException;
//import com.paisaManagement.exception.InsignificantFundException;
//import com.paisaManagement.exception.UserNotFoundException;
//import com.paisaManagement.model.Bank;
//import com.paisaManagement.model.User;
//import com.paisaManagement.repository.BankRepository;
//import com.paisaManagement.repository.UserRepository;
//import com.paisaManagement.request.BankRequest;
//import com.paisaManagement.service.BankService;
//import com.paisaManagement.util.UpdateNameEmail;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//@AllArgsConstructor
//public class BankServiceImpl implements BankService {
//
//    private final BankRepository bankRepository;
//    private final UserRepository userRepository;
//    private final JWTService jwtService;
//
//
//    @Override
//    public Bank addBank(BankRequest bankRequest) {
//        User user = userRepository.findByEmail(bankRequest.getEmail());
//        Bank existingBank=bankRepository.findByAccountNumber(UpdateNameEmail.allLetterInUpperCase(bankRequest.getAccountNumber()));
//        if(existingBank!=null){
//            throw new BankAlreadyExistsException("Bank already exist: "+bankRequest.getAccountNumber());
//        }
//        if (user == null) {
//            throw new UserNotFoundException("User not found with email: " + bankRequest.getEmail());
//        }
//        try{
//            Bank bank = new Bank();
//            bank.setBankName(bankRequest.getBankName());
//            bank.setAccountHolderName(UpdateNameEmail.updateName(bankRequest.getAccountHolderName()));
//            bank.setAccountNumber(UpdateNameEmail.allLetterInUpperCase(bankRequest.getAccountNumber()));
//
//            bank.setPhonePay(bankRequest.isPhonePay());
//            bank.setGooglePay(bankRequest.isGooglePay());
//            bank.setPaytm(bankRequest.isPaytm());
//            bank.setBhimUpi(bankRequest.isBhimUpi());
//
//            if (bankRequest.getAmount() != null && !bankRequest.getAmount().trim().isEmpty()) {
//                bank.setAmount(Float.parseFloat(bankRequest.getAmount()));
//            } else {
//                bank.setAmount(0f); // or default
//            }
//
//            if (bankRequest.getMobileNumber() != null && !bankRequest.getMobileNumber().trim().isEmpty()) {
//                bank.setMobileNumber(Long.parseLong(bankRequest.getMobileNumber()));
//            }
//
////            System.out.println(bankRequest.isActive());
//            bank.setActive(bankRequest.isActive());
//            bank.setUser(user);
//            return bankRepository.save(bank);
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//    @Override
//    public List<Bank> getAllBank(String token) {
//        token = token.substring(7);
//        String email = jwtService.extractUsername(token);
//        User user = userRepository.findByEmail(email);
////        System.out.println("get-all"+user.toString());
//        if (user == null) {
//            throw new UserNotFoundException("User not found with email: " + email);
//        }
////        System.out.println(bankRepository.findByUser_Id(user.getId()).toString());
//        return bankRepository.findByUser_Id(user.getId());
//    }
//
//    @Override
//    public Bank editBank(BankRequest bankRequest) {
//        Bank bank = bankRepository.findById(bankRequest.getId())
//                .orElseThrow(() -> new UserNotFoundException("Bank not found."));
//
//        bank.setBankName(bankRequest.getBankName());
//        bank.setAccountHolderName(UpdateNameEmail.updateName(bankRequest.getAccountHolderName()));
//        bank.setAccountNumber(UpdateNameEmail.allLetterInUpperCase(bankRequest.getAccountNumber()));
//        bank.setPhonePay(bankRequest.isPhonePay());
//        bank.setActive(bankRequest.isActive());
//        bank.setGooglePay(bankRequest.isGooglePay());
//        bank.setPaytm(bankRequest.isPaytm());
//        bank.setBhimUpi(bankRequest.isBhimUpi());
//        if (bankRequest.getMobileNumber() != null && !bankRequest.getMobileNumber().trim().isEmpty()) {
//            bank.setMobileNumber(Long.parseLong(bankRequest.getMobileNumber()));
//        }
//        return bankRepository.save(bank);
//
//    }
//
//    @Override
//    public void deleteBank(Long id) {
////        System.out.println("test");
//        Bank bank=bankRepository.findById(id).orElseThrow(()->new UserNotFoundException("Bank not found."));
//        bankRepository.delete(bank);
//    }
//
//    @Override
//    public float debitAmount(Long id, String amount) {
//        Bank bank=bankRepository.findById(id).orElseThrow(()->new UserNotFoundException("Bank not found."));
//        float actualAmount=bank.getAmount();
//        if(actualAmount<Float.parseFloat(amount)){
//            throw new InsignificantFundException("Insufficient Amount.");
//        }
//        bank.setAmount(actualAmount-Float.parseFloat(amount));
//        bankRepository.save(bank);
//        return actualAmount-Float.parseFloat(amount);
//    }
//
//    @Override
//    public float creditAmount(Long id, String amount) {
//        Bank bank=bankRepository.findById(id).orElseThrow(()->new UserNotFoundException("Bank not found."));
//        bank.setAmount(bank.getAmount()+Float.parseFloat(amount));
//        bankRepository.save(bank);
//        return bank.getAmount()+Float.parseFloat(amount);
//    }
//
//}



package com.paisaManagement.serviceImpl;

import com.paisaManagement.configuration.JWTService;
import com.paisaManagement.exception.BankAlreadyExistsException;
import com.paisaManagement.exception.InsignificantFundException;
import com.paisaManagement.exception.UserNotFoundException;
import com.paisaManagement.model.Bank;
import com.paisaManagement.model.User;
import com.paisaManagement.repository.BankRepository;
import com.paisaManagement.repository.UserRepository;
import com.paisaManagement.request.BankRequest;
import com.paisaManagement.service.BankService;
import com.paisaManagement.util.UpdateNameEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;

    @Override
    public Bank addBank(BankRequest bankRequest) {
        User user = userRepository.findByEmail(bankRequest.getEmail());
        Bank existingBank = bankRepository.findByAccountNumber(UpdateNameEmail.allLetterInUpperCase(bankRequest.getAccountNumber()));
        if (existingBank != null) {
            throw new BankAlreadyExistsException("Bank already exist: " + bankRequest.getAccountNumber());
        }
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + bankRequest.getEmail());
        }
        try {
            Bank bank = new Bank();
            bank.setBankName(bankRequest.getBankName());
            bank.setAccountHolderName(UpdateNameEmail.updateName(bankRequest.getAccountHolderName()));
            bank.setAccountNumber(UpdateNameEmail.allLetterInUpperCase(bankRequest.getAccountNumber()));

            bank.setPhonePay(bankRequest.isPhonePay());
            bank.setGooglePay(bankRequest.isGooglePay());
            bank.setPaytm(bankRequest.isPaytm());
            bank.setBhimUpi(bankRequest.isBhimUpi());

            if (bankRequest.getAmount() != null && !bankRequest.getAmount().trim().isEmpty()) {
                bank.setAmount(new BigDecimal(bankRequest.getAmount()));
            } else {
                bank.setAmount(BigDecimal.ZERO);
            }

            if (bankRequest.getMobileNumber() != null && !bankRequest.getMobileNumber().trim().isEmpty()) {
                bank.setMobileNumber(Long.parseLong(bankRequest.getMobileNumber()));
            }

            bank.setActive(bankRequest.isActive());
            bank.setUser(user);
            return bankRepository.save(bank);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bank> getAllBank(String token) {
        token = token.substring(7);
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        return bankRepository.findByUser_Id(user.getId());
    }

    @Override
    public Bank editBank(BankRequest bankRequest) {
        Bank bank = bankRepository.findById(bankRequest.getId())
                .orElseThrow(() -> new UserNotFoundException("Bank not found."));

        bank.setBankName(bankRequest.getBankName());
        bank.setAccountHolderName(UpdateNameEmail.updateName(bankRequest.getAccountHolderName()));
        bank.setAccountNumber(UpdateNameEmail.allLetterInUpperCase(bankRequest.getAccountNumber()));
        bank.setPhonePay(bankRequest.isPhonePay());
        bank.setActive(bankRequest.isActive());
        bank.setGooglePay(bankRequest.isGooglePay());
        bank.setPaytm(bankRequest.isPaytm());
        bank.setBhimUpi(bankRequest.isBhimUpi());
        if (bankRequest.getMobileNumber() != null && !bankRequest.getMobileNumber().trim().isEmpty()) {
            bank.setMobileNumber(Long.parseLong(bankRequest.getMobileNumber()));
        }
        return bankRepository.save(bank);
    }

    @Override
    public void deleteBank(Long id) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Bank not found."));
        bankRepository.delete(bank);
    }

    @Override
    public float debitAmount(Long id, String amount) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Bank not found."));

        BigDecimal actualAmount = bank.getAmount();
        BigDecimal debit = new BigDecimal(amount);

        if (actualAmount.compareTo(debit) < 0) {
            throw new InsignificantFundException("Insufficient Amount.");
        }

        BigDecimal updated = actualAmount.subtract(debit);
        bank.setAmount(updated);
        bankRepository.save(bank);

        return updated.floatValue(); // keeping return type float as required
    }

    @Override
    public float creditAmount(Long id, String amount) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Bank not found."));

        BigDecimal credit = new BigDecimal(amount);
        BigDecimal updated = bank.getAmount().add(credit);

        bank.setAmount(updated);
        bankRepository.save(bank);

        return updated.floatValue(); // keeping return type float as required
    }

    @Override
    public Bank getBankDetailsById(Long id) {
        return bankRepository.findById(id).orElseThrow(()->new UserNotFoundException("Bank not found."));

    }
}
