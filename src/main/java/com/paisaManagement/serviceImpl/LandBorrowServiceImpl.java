package com.paisaManagement.serviceImpl;

import com.paisaManagement.configuration.JWTService;
import com.paisaManagement.exception.UserNotFoundException;
import com.paisaManagement.model.LendBorrow;
import com.paisaManagement.model.User;
import com.paisaManagement.repository.LendBorrowRepository;
import com.paisaManagement.repository.UserRepository;
import com.paisaManagement.request.LendBorrowRequest;
import com.paisaManagement.service.LendBorrowService;
import com.paisaManagement.util.UpdateNameEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LandBorrowServiceImpl implements LendBorrowService {

    private final UserRepository userRepository;
    private final LendBorrowRepository expenseRepository;
    private final JWTService jwtService;

    @Override
    public void lendBorrowAdd(LendBorrowRequest lendBorrowRequest) {
        User user = userRepository.findByEmail(lendBorrowRequest.getEmail());
        if(user==null){
            throw new UserNotFoundException("User not found");
        }
//        System.out.println(expenseRequest);
        try{
            LendBorrow lendBorrow=new LendBorrow();
            lendBorrow.setLendDate(lendBorrowRequest.getLendDate());
            lendBorrow.setReason(UpdateNameEmail.updateName(lendBorrowRequest.getReason()));
            lendBorrow.setAmount(new BigDecimal(lendBorrowRequest.getAmount()));
            lendBorrow.setRequirement(lendBorrowRequest.getRequirement());  // ✅ fixed
            lendBorrow.setBank(lendBorrowRequest.getBank());                // ✅ fixed
            lendBorrow.setReturnDate(lendBorrowRequest.getReturnDate());    // ✅ fixed
            lendBorrow.setReturnName(UpdateNameEmail.updateName(lendBorrowRequest.getReturnName()));
            lendBorrow.setReturnStatus(lendBorrowRequest.getReturnStatus());
            lendBorrow.setUser(user); // ✅ link to user

            expenseRepository.save(lendBorrow);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<LendBorrow> lendBorrowGetAll(String jwt) {
        String email = jwtService.extractUsername(jwt);
        if(email==null){
            throw new UserNotFoundException("User not found.");
        }
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return new ArrayList<>(user.getLendBorrows());
    }

    @Override
    public void lendBorrowEdit(LendBorrowRequest lendBorrowRequest) {
        try{
            LendBorrow lendBorrow = expenseRepository.findById(lendBorrowRequest.getId())
                    .orElseThrow(() -> new RuntimeException("Expense not found with id: " + lendBorrowRequest.getId()));
            lendBorrow.setAmount(new BigDecimal(lendBorrowRequest.getAmount()));
            lendBorrow.setBank(lendBorrowRequest.getBank());
            lendBorrow.setLendDate(lendBorrowRequest.getLendDate());
            lendBorrow.setReason(UpdateNameEmail.updateName(lendBorrowRequest.getReason()));
            lendBorrow.setRequirement(lendBorrowRequest.getRequirement());
            lendBorrow.setReturnName(UpdateNameEmail.updateName(lendBorrowRequest.getReturnName()));
            lendBorrow.setReturnDate(lendBorrowRequest.getReturnDate());
            lendBorrow.setReturnStatus(lendBorrowRequest.getReturnStatus());
            expenseRepository.save(lendBorrow);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void lendBorrowDelete(LendBorrowRequest lendBorrowRequest) {
        LendBorrow lendBorrow=expenseRepository.findById(lendBorrowRequest.getId()).orElseThrow(() -> new RuntimeException("Expense not found with id: " + lendBorrowRequest.getId()));

        try{
            expenseRepository.delete(lendBorrow);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
