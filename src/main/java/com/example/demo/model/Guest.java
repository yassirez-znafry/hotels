package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "guest_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    @Email
    private String email;
    private Long phone;
    private String city;
    private String country;

}
