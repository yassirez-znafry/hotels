package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomStatusInfos {
    private Long roomStatusId;
    private String roomStatusName;
    private String roomStatusDescription;
}
