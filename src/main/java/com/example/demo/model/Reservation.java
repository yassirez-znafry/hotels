package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false, referencedColumnName = "room_id",
            foreignKey = @ForeignKey(name = "reservation_fk1"))
    private Room room;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "reservation_fk2"))
    private User user;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date reservationDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date reservationCheckInDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date reservationCheckOutDate;
    private Long adultsNumber;
    private Long childrenNumber;


}
