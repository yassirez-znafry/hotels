package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String userName;
    @NotBlank(message = "Password is required")
    private String userPassword;
    @Email
    @NotEmpty(message = "Email is required")
    @Column(unique = true)
    private String userEmail;
    @Nullable
    private Instant dateCreateUser;
    @Nullable
    private boolean userEnabled;

    private Integer accessLevel;
    private String image;

}
