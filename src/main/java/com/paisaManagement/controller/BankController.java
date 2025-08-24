package com.paisaManagement.controller;

import com.paisaManagement.exception.BankAlreadyExistsException;
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
    public ResponseEntity<?> addBank(@RequestHeader("Authorization") String token,
                                     @RequestBody BankRequest bankRequest){

        try{
//            System.out.println(bankRequest);
            return new ResponseEntity<>(bankService.addBank(bankRequest),HttpStatus.OK);
        }
        catch (BankAlreadyExistsException e){
            throw new BankAlreadyExistsException("Bank already exist!");
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all-bank")
    public ResponseEntity<?> getAllBank(@RequestHeader("Authorization")String token){
        return new ResponseEntity<>(bankService.getAllBank(token),HttpStatus.OK);
    }

    @PatchMapping("/edit-bank")
    public ResponseEntity<?> editBank(@RequestHeader("Authorization")String token,
                                      @RequestBody BankRequest bankRequest){
        return new ResponseEntity<>(bankService.editBank(bankRequest),HttpStatus.OK);
    }

    @DeleteMapping("/delete-bank/{id}")
    public ResponseEntity<?> deleteBank(@RequestHeader("Authorization") String token,
                                        @PathVariable Long id) {
//        System.out.println(bankRequest);
        bankService.deleteBank(id);
        return new ResponseEntity<>("Bank deleted successfully",HttpStatus.OK);
    }

    @PatchMapping("/debit-amount/{id}/debit")
    public ResponseEntity<?> debitAmount(@RequestHeader("Authorization") String token,
                                         @PathVariable Long id,@RequestParam String amount){

        return new ResponseEntity<>(bankService.debitAmount(id,amount),HttpStatus.OK);
    }

    @PatchMapping("/credit-amount/{id}/credit")
    public ResponseEntity<?> creditAmount(@RequestHeader("Authorization") String token,
                                         @PathVariable Long id,@RequestParam String amount){

        return new ResponseEntity<>(bankService.creditAmount(id,amount),HttpStatus.OK);
    }

    @GetMapping("id-bank/{id}")
    public ResponseEntity<?> bankDetailsById(@RequestHeader("Authorization") String token,
                                             @PathVariable Long id){
        return new ResponseEntity<>(bankService.getBankDetailsById(id),HttpStatus.OK);
    }

}
