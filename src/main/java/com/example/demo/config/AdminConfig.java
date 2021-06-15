package com.example.demo.config;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@Configuration
@AllArgsConstructor
public class AdminConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User user = new User();
            user.setUserName("manager");
            user.setUserEmail("manager@hotelmanager.com");
            user.setUserPassword(passwordEncoder.encode("strong_password"));
            user.setDateCreateUser(Instant.now());
            user.setAccessLevel(2);   // 2 for manager, 1 for hotel desk service and 0 for the client
            user.setUserEnabled(true);


            repository.save(user);
        };

    }

}
