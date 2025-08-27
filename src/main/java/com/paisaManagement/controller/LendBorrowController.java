package com.paisaManagement.controller;

import com.paisaManagement.model.LendBorrow;
import com.paisaManagement.request.LendBorrowRequest;
import com.paisaManagement.service.LendBorrowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class LendBorrowController {
    private final LendBorrowService expenseService;

    @PostMapping("/add-lend")
    public ResponseEntity<?> lendBorrowAdd(@RequestHeader("Authorization") String token,
                                        @RequestBody LendBorrowRequest lendBorrowRequest){
        try{
            expenseService.lendBorrowAdd(lendBorrowRequest);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException("Internal server Error");
        }

    }

    @GetMapping("/get-lend")
    public ResponseEntity<?> lendBorrowGetAll(@RequestHeader("Authorization") String token){
        try{
            String jwt = token.substring(7);
            List<LendBorrow> expenseRequests=expenseService.lendBorrowGetAll(jwt);
            return new ResponseEntity<>(expenseRequests,HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/edit-lend")
    public ResponseEntity<?> lendBorrowEdit(@RequestHeader("Authorization") String token,
                                          @RequestBody LendBorrowRequest lendBorrowRequest){
        try{
            expenseService.lendBorrowEdit(lendBorrowRequest);
            return new ResponseEntity<>("successful", HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete-lend")
    public ResponseEntity<?> lendBorrowDelete(@RequestHeader("Authorization") String token,
                                            @RequestBody LendBorrowRequest lendBorrowRequest){

        try{
            expenseService.lendBorrowDelete(lendBorrowRequest);
            return new ResponseEntity<>("Successful",HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
