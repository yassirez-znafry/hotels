package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private Long roomNumber;
    private Long roomPrice;

    @ManyToOne
    @JoinColumn(name = "roomtype_id", nullable = false, referencedColumnName = "roomtype_id",
            foreignKey = @ForeignKey(name = "room_fk1"))
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "roomstatus_id", nullable = false, referencedColumnName = "roomstatus_id",
            foreignKey = @ForeignKey(name = "room_fk2"))
    public RoomStatus roomStatus;

    @OneToMany(mappedBy = "id", targetEntity = Reservation.class)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "id", targetEntity = Rent.class)
    private List<Rent> rents;

 }
