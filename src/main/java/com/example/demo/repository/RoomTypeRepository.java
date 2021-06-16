package com.example.demo.repository;

import com.example.demo.model.Reservation;
import com.example.demo.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    Optional<RoomType> findByRoomTypeName(String roomType);
}
