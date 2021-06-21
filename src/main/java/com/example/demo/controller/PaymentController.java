package com.example.demo.controller;


import com.example.demo.dto.PaymentInfos;
import com.example.demo.exceptions.SpringHotelManagerException;
import com.example.demo.model.Payment;
import com.example.demo.model.Rent;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import com.example.demo.repository.RentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final AuthService authService;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;

    @GetMapping("/sum/{rent_id}")
    public PaymentInfos getPayment(@PathVariable Long rent_id) {
        Optional<Rent> rent = rentRepository.findById(rent_id);
        rent.orElseThrow(() -> new SpringHotelManagerException("Rent with this id does not exist!!"));

        Reservation reservation = rent.get().getReservation();

        // getting milliseconds for both dates
        Long date1InMs = reservation.getReservationCheckInDate().getTime();
        Long date2InMs = reservation.getReservationCheckOutDate().getTime();

        // getting the diff between two dates.
        Long timeDiff = 0L;
        if(date1InMs > date2InMs) {
            timeDiff = date1InMs - date2InMs;
        } else {
            timeDiff = date2InMs - date1InMs;
        }

        // converting diff into days
        Long daysDiff = (Long) (timeDiff / (1000 * 60 * 60* 24));
        Long price = reservation.getRoom().getRoomPrice();

        PaymentInfos paymentInfos = new PaymentInfos();
        paymentInfos.setRentId(rent_id);
        paymentInfos.setUserId(reservation.getUser().getUserId());
        paymentInfos.setPaymentDate(new Date(System.currentTimeMillis()));
        paymentInfos.setSum(price*daysDiff);

        return paymentInfos;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPayment(@RequestBody PaymentInfos paymentInfos) {
        if (authService.getCurrentUser().getAccessLevel() >= 1) {
            paymentService.addPayment(paymentInfos);
            return new ResponseEntity<>("Payment added successfully", OK);
        }
        return new ResponseEntity<>("Payment not added", FORBIDDEN);
    }
}
