package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomInfos {
    private Long roomId;
    private Long roomNumber;
    private Long roomPrice;
    private String roomType;
    private String roomStatus;
}
