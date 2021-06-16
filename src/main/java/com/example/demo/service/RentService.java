package com.example.demo.service;


import com.example.demo.dto.RentInfos;
import com.example.demo.dto.ReservationInfos;
import com.example.demo.dto.UserInfos;
import com.example.demo.exceptions.SpringHotelManagerException;
import com.example.demo.model.Rent;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.repository.RentRepository;
import com.example.demo.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class RentService {
    private final RentRepository rentRepository;
    private final ReservationRepository reservationRepository;

    private final AuthService authService;

    public void addRent(ReservationInfos reservationInfos){
        Optional<Reservation> reservation = reservationRepository.findById(reservationInfos.getReservationId());
        reservation.orElseThrow(() -> new SpringHotelManagerException("Reservation not found!!"));

//        User currentUser = authService.getCurrentUser();
//
//        if(!currentUser.getUserEmail().equals(reservation.get().getUser().getUserEmail())){
//            throw new SpringHotelManagerException("Access denied!!");
//        }

        Rent rent = new Rent();
        rent.setPaid(false);
        rent.setReservation(reservation.get());

        rentRepository.save(rent);
    }

    public void cancelRent(RentInfos rentInfos){

//        Optional<Reservation> reservation = reservationRepository.findById(rentInfos.getReservationId());
//        reservation.orElseThrow(() -> new SpringHotelManagerException("Reservation not found!!"));

        Optional<Rent> rent = rentRepository.findById(rentInfos.getId());
        rent.orElseThrow(() -> new SpringHotelManagerException("Rent not found!!"));

//        User currentUser = authService.getCurrentUser();
//
//        if(!currentUser.getUserEmail().equals(reservation.get().getUser().getUserEmail())){
//            throw new SpringHotelManagerException("Access denied!!");
//        }

        rentRepository.delete(rent.get());

    }
}
