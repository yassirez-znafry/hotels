package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne
    private Reservation reservation;

    @OneToOne
    private Guest guest;
}
