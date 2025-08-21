package com.paisaManagement.controller;

import com.paisaManagement.request.BankRequest;
import com.paisaManagement.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping("/add-bank")
    public ResponseEntity<?> addBank(@RequestHeader("Authentication") String token,
                                     @RequestBody BankRequest bankRequest){
        try{
            return new ResponseEntity<>(bankService.addBank(bankRequest), HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
