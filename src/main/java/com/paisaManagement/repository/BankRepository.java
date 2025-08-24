package com.paisaManagement.repository;

import com.paisaManagement.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank,Long> {
    Bank findByAccountNumber(String accountNumber);
    List<Bank> findByUser_Id(Long id);
}
