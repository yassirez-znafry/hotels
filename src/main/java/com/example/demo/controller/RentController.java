package com.example.demo.controller;

import com.example.demo.dto.RentInfos;
import com.example.demo.dto.ReservationInfos;
import com.example.demo.model.Rent;
import com.example.demo.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/rent")
@AllArgsConstructor
public class RentController {
    private final RentService rentService;

    @GetMapping("/")
    public List<RentInfos> getAllRents(){
        List<Rent> rents = rentService.getAllRents();
        return mapRents(rents);
    }

    @GetMapping("/user/{id}")
    public List<RentInfos> getAllRents(@PathVariable Long id){
        List<Rent> rents = rentService.getAllRentsByUserId(id);
        return mapRents(rents);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRent(@RequestBody ReservationInfos reservationInfos){
        rentService.addRent(reservationInfos);
        return new ResponseEntity<>("Rent added successfully", OK);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> deleteRent(@RequestBody RentInfos rentInfos){
        rentService.cancelRent(rentInfos);
        return new ResponseEntity<>("Rent deleted successfully", OK);
    }

    public List<RentInfos> mapRents(List<Rent> rents){
        List<RentInfos> rentInfosList = new ArrayList<RentInfos>();
        for(Rent rent: rents){
            RentInfos rentInfos = new RentInfos();
            rentInfos.setId(rent.getId());
            rentInfos.setPaid(rent.getPaid());
            rentInfos.setReservationId(rent.getReservation().getId());

            rentInfosList.add(rentInfos);
        }

        return rentInfosList;
    }
}
