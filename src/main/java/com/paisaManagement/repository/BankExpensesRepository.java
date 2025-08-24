package com.paisaManagement.repository;

import com.paisaManagement.model.BankExpenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankExpensesRepository extends JpaRepository<BankExpenses,Long> {
    List<BankExpenses> findAllByBankId(Long id);

}
