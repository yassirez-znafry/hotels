package com.example.demo.controller;

import com.example.demo.dto.RentInfos;
import com.example.demo.dto.ReservationInfos;
import com.example.demo.model.Rent;
import com.example.demo.service.AuthService;
import com.example.demo.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/rent")
@AllArgsConstructor
public class RentController {
    private final RentService rentService;
    private final AuthService authService;

    @GetMapping("/")
    public List<RentInfos> getAllRents() {
        if (authService.getCurrentUser().getAccessLevel() >= 1) {
            List<Rent> rents = rentService.getAllRents();
            return mapRents(rents);
        }
        return null;
    }

    @GetMapping("/user/{id}")
    public List<RentInfos> getAllRents(@PathVariable Long id){
        if (authService.getCurrentUser().getAccessLevel() >= 1) {
            List<Rent> rents = rentService.getAllRentsByUserId(id);
            return mapRents(rents);
        }
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRent(@RequestBody ReservationInfos reservationInfos) {
        if (authService.getCurrentUser().getAccessLevel() >= 1) {
            rentService.addRent(reservationInfos);
            return new ResponseEntity<>("Rent added successfully", OK);
        }
        return  new ResponseEntity<>("Rent not added", FORBIDDEN);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> deleteRent(@RequestBody RentInfos rentInfos) {
        if (authService.getCurrentUser().getAccessLevel() >= 1) {
            rentService.cancelRent(rentInfos);
            return new ResponseEntity<>("Rent deleted successfully", OK);
        }
        return  new ResponseEntity<>("Rent not deleted", FORBIDDEN);
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
