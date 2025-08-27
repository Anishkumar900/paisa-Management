package com.paisaManagement.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LendBorrowRequest {

    private Long id;
    private String email;
    private String lendDate;
    private String reason;
    private String amount;
    private String requirement;
    private String bank;
    private String returnDate;
    private String returnName;
    private String returnStatus;
}
