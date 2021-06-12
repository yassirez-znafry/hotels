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
public class RoomType {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "roomtype_id")
    private Long id;

    private String roomTypeName;
    private String roomTypeDescription;

    @OneToMany(mappedBy = "id", orphanRemoval = true, cascade = CascadeType.ALL,
    fetch = FetchType.LAZY, targetEntity = Room.class)
    private List<Room> rooms;

}
