package com.paisaManagement.repository;

import com.paisaManagement.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank,Long> {
}
