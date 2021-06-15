package com.example.demo.config;

import com.example.demo.model.Room;
import com.example.demo.model.RoomStatus;
import com.example.demo.model.RoomType;
import com.example.demo.model.User;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomStatusRepository;
import com.example.demo.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
@AllArgsConstructor
public class RoomConfig {

    private final RoomRepository roomRepository;
    private final RoomStatusRepository roomStatusRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Bean
    CommandLineRunner roomCommandLineRunner(){
        return args -> {

            RoomStatus rs1 = new RoomStatus();
            rs1.setRoomStatusName("Available");
            rs1.setRoomStatusDescription("Room Empty and can be reserved");
            RoomStatus rs2 = new RoomStatus();
            rs2.setRoomStatusName("Occupied");
            rs2.setRoomStatusDescription("Room occupied");
            roomStatusRepository.saveAll(List.of(rs1, rs2));


            RoomType rt1 = new RoomType();
            rt1.setRoomTypeName("Single");
            rt1.setRoomTypeDescription("Room assigned to one person");
            RoomType rt2 = new RoomType();
            rt2.setRoomTypeName("Double");
            rt2.setRoomTypeDescription("Room assigned to two person");
            RoomType rt3 = new RoomType();
            rt3.setRoomTypeName("Triple");
            rt3.setRoomTypeDescription("Room assigned to three person");
            RoomType rt4 = new RoomType();
            rt4.setRoomTypeName("Quad");
            rt4.setRoomTypeDescription("Room assigned to four person");
            RoomType rt5 = new RoomType();
            rt5.setRoomTypeName("Queen");
            rt5.setRoomTypeDescription("Room with a queen-sized bed");
            RoomType rt6 = new RoomType();
            rt6.setRoomTypeName("King");
            rt6.setRoomTypeDescription("Room with a king-sized bed");
            roomTypeRepository.saveAll(List.of(rt1, rt2, rt3, rt4, rt5, rt6));

            Room a = new Room();
            a.setRoomNumber(1L);
            a.setRoomPrice(100L);
            a.setRoomType(rt3);
            a.setRoomStatus(rs1);

            Room b = new Room();
            b.setRoomNumber(2L);
            b.setRoomPrice(800L);
            b.setRoomType(rt6);
            b.setRoomStatus(rs1);


            roomRepository.saveAll(
                    List.of(a, b)
            );
        };

    }
}
