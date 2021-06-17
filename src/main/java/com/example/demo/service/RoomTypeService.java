package com.example.demo.service;

import com.example.demo.dto.RoomTypeInfos;
import com.example.demo.model.RoomType;
import com.example.demo.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    public List<RoomTypeInfos> getAllRoomTypes(){
        List<RoomType> roomTypeList = roomTypeRepository.findAll();

        List<RoomTypeInfos> roomTypeInfosList = new ArrayList<RoomTypeInfos>();

        for(RoomType roomType: roomTypeList){
            RoomTypeInfos roomTypeInfos = new RoomTypeInfos();
            roomTypeInfos.setRoomTypeId(roomType.getId());
            roomTypeInfos.setRoomTypeName(roomType.getRoomTypeName());
            roomTypeInfos.setRoomTypeDescription(roomType.getRoomTypeDescription());

            roomTypeInfosList.add(roomTypeInfos);
        }

        return roomTypeInfosList;
    }
}
