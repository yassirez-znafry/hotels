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
public class RoomStatus {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "roomstatus_id")
    private Long id;

    private String roomStatusName;
    private String roomStatusDescription;

    @OneToMany(mappedBy = "roomStatus", orphanRemoval = true, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, targetEntity = Room.class)
    private List<Room> rooms;
}
