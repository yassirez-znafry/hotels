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

            Room r3 = new Room();
            r3.setRoomNumber(3L);
            r3.setRoomPrice(200L);
            r3.setRoomType(rt1);
            r3.setRoomStatus(rs1);

            Room r4 = new Room();
            r4.setRoomNumber(3L);
            r4.setRoomPrice(200L);
            r4.setRoomType(rt1);
            r4.setRoomStatus(rs1);

            Room r5 = new Room();
            r5.setRoomNumber(3L);
            r5.setRoomPrice(200L);
            r5.setRoomType(rt1);
            r5.setRoomStatus(rs1);

            Room r6 = new Room();
            r6.setRoomNumber(3L);
            r6.setRoomPrice(200L);
            r6.setRoomType(rt1);
            r6.setRoomStatus(rs1);

            Room r7 = new Room();
            r7.setRoomNumber(3L);
            r7.setRoomPrice(200L);
            r7.setRoomType(rt1);
            r7.setRoomStatus(rs1);

            Room r8 = new Room();
            r8.setRoomNumber(3L);
            r8.setRoomPrice(200L);
            r8.setRoomType(rt1);
            r8.setRoomStatus(rs1);

            Room r9 = new Room();
            r9.setRoomNumber(3L);
            r9.setRoomPrice(200L);
            r9.setRoomType(rt1);
            r9.setRoomStatus(rs1);


            roomRepository.saveAll(
                    List.of(a, b, r3, r4, r5, r6, r7, r8, r9)
            );
        };

    }
}
