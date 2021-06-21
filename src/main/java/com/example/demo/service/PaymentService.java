package com.example.demo.service;

import com.example.demo.dto.PaymentInfos;
import com.example.demo.exceptions.SpringHotelManagerException;
import com.example.demo.model.Payment;
import com.example.demo.model.Rent;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.RentRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final RentRepository rentRepository;
    private final UserRepository userRepository;


    public void addPayment(PaymentInfos paymentInfos){
        Optional<Rent> rent = rentRepository.findById(paymentInfos.getRentId());
        rent.orElseThrow(() -> new SpringHotelManagerException("Rent with this id does not exist!!"));

        Optional<User> user = userRepository.findById(paymentInfos.getUserId());
        user.orElseThrow(() -> new SpringHotelManagerException("User with this id does not exist!!"));

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

        Payment payment = new Payment();
        payment.setSum(daysDiff*price);
        payment.setRent(rent.get());
        payment.setUser(user.get());
        payment.setPaymentDate(new Date(System.currentTimeMillis()));

        rent.get().setPaid(true);
        rentRepository.save(rent.get());
        paymentRepository.save(payment);


    }

}
