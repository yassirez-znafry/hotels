package com.example.demo.repository;

import com.example.demo.model.Reservation;
import com.example.demo.model.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {
    Optional<RoomStatus> findByRoomStatusName(String roomStatus);
}
