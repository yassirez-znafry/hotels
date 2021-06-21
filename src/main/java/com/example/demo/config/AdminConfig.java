package com.example.demo.config;


import com.example.demo.model.NotificationEmail;
import com.example.demo.model.User;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationTokenRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class AdminConfig {

    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final MailService mailService;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User user = new User();
            user.setUserName("manager");
            user.setUserEmail("manager@hotelmanager.com");
            user.setUserPassword(passwordEncoder.encode("strong_password"));
            user.setDateCreateUser(Instant.now());
            user.setAccessLevel(2);   // 2 for manager, 1 for hotel desk service and 0 for the client
            user.setUserEnabled(false);
            repository.save(user);

            String token = authService.generateVerificationToken(user);
            mailService.sendMail(new NotificationEmail("please activate your account", user.getUserEmail(), "Thank you for signing up to HotelManager, " +
                    "please click on the below url to activate your account : " +
                    "http://localhost:8080/api/auth/accountVerification/" + token));
        };

    }

}
