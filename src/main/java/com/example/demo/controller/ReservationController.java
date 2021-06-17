package com.example.demo.controller;

import com.example.demo.dto.ReservationInfos;
import com.example.demo.dto.RoomInfos;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping(path = "/")
    public List<ReservationInfos> getAllReservations(){
        List<Reservation> allReservations = reservationService.getAllReservations();
        return mapReservations(allReservations);
    }

    @GetMapping(path = "/current_user")
    public List<ReservationInfos> getAllReservationsForCurrentUser(){
        List<Reservation> allReservations = reservationService.getAllReservationForCurrentUser();
        return mapReservations(allReservations);
    }

    @GetMapping(path = "/user/{id}")
    public List<ReservationInfos> getAllReservationsByUser(@PathVariable  Long id){
        List<Reservation> allReservations = reservationService.getAllReservationsByUserId(id);
        return mapReservations(allReservations);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReservation(@RequestBody ReservationInfos reservationInfos){
        reservationService.addReservationForCurrentUser(reservationInfos);
        return new ResponseEntity<>("Reservation added successfully", OK);
    }

    @PostMapping("/modify")
    public ResponseEntity<String> modifyReservation(@RequestBody ReservationInfos reservationInfos){
        reservationService.modifyReservationForCurrentUser(reservationInfos);
        return new ResponseEntity<>("Reservation modification successful", OK);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> deleteReservation(@RequestBody ReservationInfos reservationInfos){
        reservationService.deleteReservationForCurrentUser(reservationInfos);
        return new ResponseEntity<>("Reservation deleted successfully", OK);
    }


    public List<ReservationInfos> mapReservations(List<Reservation> allReservations){
        List<ReservationInfos> allReservationInfos = new ArrayList<ReservationInfos>();
        for(Reservation reservation : allReservations){
            ReservationInfos reservationInfos = new ReservationInfos();
            reservationInfos.setReservationId(reservation.getId());
            reservationInfos.setRoomId(reservation.getRoom().getId());
            reservationInfos.setUserId(reservation.getUser().getUserId());
            reservationInfos.setAdultsNumber(reservation.getAdultsNumber());
            reservationInfos.setChildrenNumber(reservation.getChildrenNumber());
            reservationInfos.setReservationDate(reservation.getReservationDate());
            reservationInfos.setReservationCheckInDate(reservation.getReservationCheckInDate());
            reservationInfos.setReservationCheckOutDate(reservation.getReservationCheckOutDate());

            allReservationInfos.add(reservationInfos);
        }
        return allReservationInfos;
    }

}
