package com.example.demo.controller;

import com.example.demo.dto.RoomInfos;
import com.example.demo.dto.RoomTypeInfos;
import com.example.demo.service.RoomTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/room_type")
@AllArgsConstructor
public class RoomTypeController {
    private RoomTypeService roomTypeService;

    @GetMapping("/")
    public List<RoomTypeInfos> getAllRoomTypes(){
        return roomTypeService.getAllRoomTypes();
    }
}
