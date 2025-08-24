package com.paisaManagement.controller;


import com.paisaManagement.request.BankExpensesRequest;
import com.paisaManagement.service.BankExpensesService;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank/expenses")
@AllArgsConstructor
public class BankExpensesController {

    private final BankExpensesService bankExpensesService;

    @PostMapping("/add-expenses")
    public ResponseEntity<?> addBankExpenses(@RequestHeader("Authorization") String token,
                                             @RequestBody BankExpensesRequest bankExpensesRequest){
        return new ResponseEntity<>(bankExpensesService.addBankExpenses(bankExpensesRequest), HttpStatusCode.valueOf(HttpStatus.SC_OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getBankExpensesByBankId(@RequestHeader("Authorization") String token,
                                                     @PathVariable Long id){
        return new ResponseEntity<>(bankExpensesService.getBankExpensesByBankId(id), HttpStatusCode.valueOf(HttpStatus.SC_OK));
    }

    @DeleteMapping("/delete-expenses-bank/{id}")
    public ResponseEntity<?> deleteBankExpensesById(@RequestHeader("Authorization") String token,
                                                    @PathVariable Long id){

        bankExpensesService.deleteBankExpensesById(id);
        return new ResponseEntity<>("Delete bank expenses successful.", HttpStatusCode.valueOf(HttpStatus.SC_OK));
    }
}
