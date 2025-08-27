package com.paisaManagement.service;

import com.paisaManagement.model.LendBorrow;
import com.paisaManagement.request.LendBorrowRequest;

import java.util.List;

public interface LendBorrowService {
    void lendBorrowAdd(LendBorrowRequest lendBorrowRequest);
    List<LendBorrow> lendBorrowGetAll(String jwt);
    void lendBorrowEdit(LendBorrowRequest lendBorrowRequest);
    void lendBorrowDelete(LendBorrowRequest lendBorrowRequest);
}
