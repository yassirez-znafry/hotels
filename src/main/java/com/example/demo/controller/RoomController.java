package com.example.demo.controller;


import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RoomInfos;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/room")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

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

            allRoomsInfos.add(roomInfos);
        }
        return allRoomsInfos;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addRoom(@RequestBody RoomInfos roomInfos){
        roomService.addRoom(roomInfos);
        return new ResponseEntity<>("user registration successful", OK);
    }

    @PostMapping("/modify")
    public ResponseEntity<String> modifyRoom(@RequestBody RoomInfos roomInfos){
        roomService.modifyRoom(roomInfos);
        return new ResponseEntity<>("user modification successful", OK);
    }

    @DeleteMapping("/delete/{room_id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long room_id){
        roomService.deleteRoomById(room_id);
        return new ResponseEntity<>("user deleted successfully", OK);
    }

}
