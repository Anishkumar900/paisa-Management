package com.paisaManagement.serviceImpl;

import com.paisaManagement.configuration.JWTService;
import com.paisaManagement.exception.UserNotFoundException;
import com.paisaManagement.model.Expenses;
import com.paisaManagement.model.User;
import com.paisaManagement.repository.ExpenseRepository;
import com.paisaManagement.repository.UserRepository;
import com.paisaManagement.request.ExpenseRequest;
import com.paisaManagement.service.ExpenseService;
import com.paisaManagement.util.UpdateNameEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final JWTService jwtService;

    @Override
    public void expenseAdd(ExpenseRequest expenseRequest) {
        User user = userRepository.findByEmail(expenseRequest.getEmail());
        if(user==null){
            throw new UserNotFoundException("User not found");
        }
//        System.out.println(expenseRequest);
        try{
            Expenses expenses=new Expenses();
            expenses.setExpenseDate(expenseRequest.getExpenseDate());
            expenses.setReason(UpdateNameEmail.updateName(expenseRequest.getReason()));
            expenses.setAmount(Integer.parseInt(expenseRequest.getAmount()));
            expenses.setRequirement(expenseRequest.getRequirement());  // ✅ fixed
            expenses.setSavedAmount(Integer.parseInt(expenseRequest.getSavedAmount()));
            expenses.setBank(expenseRequest.getBank());                // ✅ fixed
            expenses.setExpediterType(expenseRequest.getExpediterType());
            expenses.setReturnDate(expenseRequest.getReturnDate());    // ✅ fixed
            expenses.setReturnName(UpdateNameEmail.updateName(expenseRequest.getReturnName()));
            expenses.setReturnStatus(expenseRequest.getReturnStatus());
            expenses.setUser(user); // ✅ link to user

            expenseRepository.save(expenses);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Expenses> getExpenses(String jwt) {
        String email = jwtService.extractUsername(jwt);
        if(email==null){
            throw new UserNotFoundException("User not found.");
        }
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }
        return new ArrayList<>(user.getExpenses());
    }

    @Override
    public void expensesEdit(ExpenseRequest expenseRequest) {
        try{
            Expenses expenses = expenseRepository.findById(expenseRequest.getId())
                    .orElseThrow(() -> new RuntimeException("Expense not found with id: " + expenseRequest.getId()));
            expenses.setAmount(Integer.parseInt(expenseRequest.getAmount()));
            expenses.setBank(expenseRequest.getBank());
            expenses.setExpediterType(expenseRequest.getExpediterType());
            expenses.setExpenseDate(expenseRequest.getExpenseDate());
            expenses.setReason(UpdateNameEmail.updateName(expenseRequest.getReason()));
            expenses.setRequirement(expenseRequest.getRequirement());
            expenses.setSavedAmount(Integer.parseInt(expenseRequest.getSavedAmount()));
            expenses.setReturnName(UpdateNameEmail.updateName(expenseRequest.getReturnName()));
            expenses.setReturnDate(expenseRequest.getReturnDate());
            expenses.setReturnStatus(expenseRequest.getReturnStatus());
            expenseRepository.save(expenses);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void expensesDelete(ExpenseRequest expenseRequest) {
        Expenses expenses=expenseRepository.findById(expenseRequest.getId()).orElseThrow(() -> new RuntimeException("Expense not found with id: " + expenseRequest.getId()));

        try{
            expenseRepository.delete(expenses);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
