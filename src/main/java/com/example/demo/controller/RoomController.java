package com.example.demo.controller;


import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RoomInfos;
import com.example.demo.model.Room;
import com.example.demo.service.AuthService;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/room")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final AuthService authService;

    @GetMapping(path = "/")
    public List<RoomInfos> getAllRooms(){
        List<Room> allRooms = roomService.getAllRooms();
        List<RoomInfos> allRoomsInfos = new ArrayList<RoomInfos>();
        for(Room room : allRooms){
            RoomInfos roomInfos = new RoomInfos();
            roomInfos.setRoomId(room.getId());
            roomInfos.setRoomNumber(room.getRoomNumber());
            roomInfos.setRoomPrice(room.getRoomPrice());
            roomInfos.setRoomType(room.getRoomType().getRoomTypeName());
            roomInfos.setRoomStatus(room.getRoomStatus().getRoomStatusName());
            roomInfos.setRoomImage(room.getRoomImage());

            allRoomsInfos.add(roomInfos);
        }
        return allRoomsInfos;
    }

    @GetMapping("/{room_id}")
    public RoomInfos getRoomById(@PathVariable Long room_id){
        Room room = roomService.getRoomById(room_id);
        RoomInfos roomInfos = new RoomInfos();
        roomInfos.setRoomId(room.getId());
        roomInfos.setRoomNumber(room.getRoomNumber());
        roomInfos.setRoomPrice(room.getRoomPrice());
        roomInfos.setRoomType(room.getRoomType().getRoomTypeName());
        roomInfos.setRoomStatus(room.getRoomStatus().getRoomStatusName());
        roomInfos.setRoomImage(room.getRoomImage());


        return roomInfos;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRoom(@RequestBody RoomInfos roomInfos){
        if(authService.getCurrentUser().getAccessLevel() == 2) {
            roomService.addRoom(roomInfos);
            return new ResponseEntity<>("Room added successfully", OK);
        }
        return new ResponseEntity<>("Room not added", HttpStatus.FORBIDDEN);

    }

    @PostMapping("/modify")
    public ResponseEntity<String> modifyRoom(@RequestBody RoomInfos roomInfos) {
        if (authService.getCurrentUser().getAccessLevel() >= 1) {
            roomService.modifyRoom(roomInfos);
            return new ResponseEntity<>("Room modification successful", OK);
        }
        return new ResponseEntity<>("Room modification unsuccessful", FORBIDDEN);
    }

    @DeleteMapping("/delete/{room_id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long room_id) {
        if (authService.getCurrentUser().getAccessLevel() == 2) {
            roomService.deleteRoomById(room_id);
            return new ResponseEntity<>("Room deleted successfully", OK);
        }
        return new ResponseEntity<>("Room deleted successfully", FORBIDDEN);
    }


}
