package com.example.demo.repository;

import com.example.demo.model.Rent;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    Optional<Rent> findByReservation(Reservation reservation);

    List<Rent> findAllByUser(User user);
}
