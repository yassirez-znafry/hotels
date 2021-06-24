package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "payment_id")
    private Long id;
    private Long sum;
    private Boolean withCash;
    private Long accountNumber;

    @OneToOne
    private Rent rent;

    @OneToOne
    private User user;

    private Date paymentDate;
}
