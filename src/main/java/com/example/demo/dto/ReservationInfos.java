package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationInfos {
    private Long reservationId;
    private Long roomId;
    private Long userId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date reservationDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date reservationCheckInDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date reservationCheckOutDate;
    private Long adultsNumber;
    private Long childrenNumber;
}
