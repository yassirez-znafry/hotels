package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationInfos {
    private Long reservationId;
    private Long roomId;
    private Long userId;
    private Timestamp reservationDate;
    private Timestamp reservationCheckInDate;
    private Timestamp reservationCheckOutDate;
    private Long adultsNumber;
    private Long childrenNumber;
}
