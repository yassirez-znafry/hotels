package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfos {
    private Long id;
    private Long sum;
    private Boolean withCash;
    private Long accountNumber;
    private Long rentId;
    private Long userId;
    private Date paymentDate;
}
