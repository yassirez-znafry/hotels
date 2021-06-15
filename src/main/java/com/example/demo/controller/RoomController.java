package com.example.demo.controller;


import com.example.demo.dto.RoomInfos;
import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
            roomInfos.setRoomNumber(room.getRoomNumber());
            roomInfos.setRoomPrice(room.getRoomPrice());
            roomInfos.setRoomType(room.getRoomType().getRoomTypeName());
            roomInfos.setRoomStatus(room.getRoomStatus().getRoomStatusName());

            allRoomsInfos.add(roomInfos);
        }
        return allRoomsInfos;
    }
}
