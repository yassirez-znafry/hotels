package com.example.demo.service;

import com.example.demo.dto.RoomInfos;
import com.example.demo.exceptions.SpringHotelManagerException;
import com.example.demo.model.Room;
import com.example.demo.model.RoomStatus;
import com.example.demo.model.RoomType;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomStatusRepository;
import com.example.demo.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Room getRoomById(Long room_id){
        Optional<Room> room = roomRepository.findById(room_id);
        room.orElseThrow(() -> new SpringHotelManagerException("Room not found!!"));
        return room.get();
    }


    public void addRoom(RoomInfos roomInfos){
        Optional<RoomType> roomType = roomTypeRepository.findRoomTypeByRoomTypeName(roomInfos.getRoomType());
//        Optional<RoomType> roomType = roomTypeRepository.findById(12L);
        roomType.orElseThrow(() -> new SpringHotelManagerException("Room type not found!!"));

        Optional<RoomStatus> roomStatus = roomStatusRepository.findByRoomStatusName(roomInfos.getRoomStatus());
        roomStatus.orElseThrow(() -> new SpringHotelManagerException("Room status not found!!"));

        Room room = new Room();
        room.setRoomNumber(roomInfos.getRoomNumber());
        room.setRoomPrice(roomInfos.getRoomPrice());
        room.setRoomType(roomType.get());
        room.setRoomStatus(roomStatus.get());

        roomRepository.save(room);
    }

    public void deleteRoomById(Long roomId){
        Optional<Room> room = roomRepository.findById(roomId);
        room.orElseThrow(() -> new SpringHotelManagerException("Room with the same number not found!!"));

        roomRepository.delete(room.get());
    }

    public void modifyRoom(RoomInfos roomInfos){

        Optional<Room> roomOptional = roomRepository.findById(roomInfos.getRoomId());
        roomOptional.orElseThrow(() -> new SpringHotelManagerException("Room with the same number not found!!"));

        Optional<RoomType> roomType = roomTypeRepository.findRoomTypeByRoomTypeName(roomInfos.getRoomType());
        roomType.orElseThrow(() -> new SpringHotelManagerException("Room type not found!!"));

        Optional<RoomStatus> roomStatus = roomStatusRepository.findByRoomStatusName(roomInfos.getRoomStatus());
        roomStatus.orElseThrow(() -> new SpringHotelManagerException("Room status not found!!"));

        Room room = roomOptional.get();
        room.setRoomStatus(roomStatus.get());
        room.setRoomType(roomType.get());
        room.setRoomNumber(roomInfos.getRoomNumber());
        room.setRoomPrice(roomInfos.getRoomPrice());


        roomRepository.save(room);
    }

}
