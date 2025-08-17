package com.paisaManagement.repository;

import com.paisaManagement.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expenses,Long> {
}
