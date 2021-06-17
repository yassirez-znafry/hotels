package com.example.demo.service;


import com.example.demo.dto.RoomStatusInfos;
import com.example.demo.dto.RoomTypeInfos;
import com.example.demo.model.RoomStatus;
import com.example.demo.model.RoomType;
import com.example.demo.repository.RoomStatusRepository;
import com.example.demo.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RoomStatusService {
    private final RoomStatusRepository roomStatusRepository;

    public List<RoomStatusInfos> getAllRoomStatuses(){
        List<RoomStatus> roomStatusList = roomStatusRepository.findAll();

        List<RoomStatusInfos> roomStatusInfosList = new ArrayList<RoomStatusInfos>();

        for(RoomStatus roomStatus: roomStatusList){
            RoomStatusInfos roomStatusInfos = new RoomStatusInfos();
            roomStatusInfos.setRoomStatusId(roomStatus.getId());
            roomStatusInfos.setRoomStatusName(roomStatus.getRoomStatusName());
            roomStatusInfos.setRoomStatusDescription(roomStatus.getRoomStatusDescription());

            roomStatusInfosList.add(roomStatusInfos);
        }

        return roomStatusInfosList;
    }
}
