package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rent_id")
    private Long id;

    private Boolean paid;

    @OneToOne
    private Reservation reservation;


}
