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
public class Claim {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "claim_id")
    private Long id;

    private String content;

    private boolean processed;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "claim_fk1"))
    private User user;
}
