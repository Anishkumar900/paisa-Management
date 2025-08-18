package com.paisaManagement.controller;

import com.paisaManagement.model.Expenses;
import com.paisaManagement.request.ExpenseRequest;
import com.paisaManagement.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping("/add-expense")
    public ResponseEntity<?> expenseAdd(@RequestHeader("Authorization") String token,
                                        @RequestBody ExpenseRequest expenseRequest){
        try{
            expenseService.expenseAdd(expenseRequest);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException("Internal server Error");
        }

    }

    @GetMapping("/get-expenses")
    public ResponseEntity<?> expensesGet(@RequestHeader("Authorization") String token){
        try{
            String jwt = token.substring(7);
            List<Expenses> expenseRequests=expenseService.getExpenses(jwt);
            return new ResponseEntity<>(expenseRequests,HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/edit-expenses")
    public ResponseEntity<?> expensesEdit(@RequestHeader("Authorization") String token,
                                          @RequestBody ExpenseRequest expenses){
        try{
            expenseService.expensesEdit(expenses);
            return new ResponseEntity<>("successful", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete-expenses")
    public ResponseEntity<?> expensesDelete(@RequestHeader("Authorization") String token,
                                            @RequestBody ExpenseRequest expenses){

        try{
            expenseService.expensesDelete(expenses);
            return new ResponseEntity<>("Successful",HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
