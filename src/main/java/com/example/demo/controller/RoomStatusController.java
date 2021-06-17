package com.example.demo.controller;


import com.example.demo.dto.RoomStatusInfos;
import com.example.demo.service.RoomStatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/room_status")
@AllArgsConstructor
public class RoomStatusController {

    private final RoomStatusService roomStatusService;

    @GetMapping("/")
    public List<RoomStatusInfos> getAllRoomStatuses(){
        return roomStatusService.getAllRoomStatuses();
    }
}
