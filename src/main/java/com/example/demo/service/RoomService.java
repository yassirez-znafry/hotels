package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomStatusRepository;
import com.example.demo.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomStatusRepository roomStatusRepository;

    public List<Room> getAllRooms(){
        List<Room> allRooms = roomRepository.findAll();
        return allRooms;
    }

}
