package com.example.demo.service;

import com.example.demo.dto.ReservationInfos;
import com.example.demo.exceptions.SpringHotelManagerException;
import com.example.demo.model.Rent;
import com.example.demo.model.Reservation;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.repository.RentRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RentRepository rentRepository;

    private final AuthService authService;

    public void addReservationForCurrentUser(ReservationInfos reservationInfos){
        Optional<Room> room = roomRepository.findById(reservationInfos.getRoomId());
        room.orElseThrow(() -> new SpringHotelManagerException("No room with the id given!!"));

        User currentUser = authService.getCurrentUser();

        Reservation reservation = new Reservation();
        reservation.setUser(currentUser);
        reservation.setRoom(room.get());
        reservation.setReservationDate(reservationInfos.getReservationDate());
        reservation.setReservationCheckInDate(reservationInfos.getReservationCheckInDate());
        reservation.setReservationCheckOutDate(reservationInfos.getReservationCheckOutDate());
        reservation.setAdultsNumber(reservationInfos.getAdultsNumber());
        reservation.setChildrenNumber(reservation.getChildrenNumber());


        reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservationForCurrentUser(){
        User currentUser = authService.getCurrentUser();
        List<Reservation> reservationList = reservationRepository.findAllByUser(currentUser);

        return reservationList;
    }

    public List<Reservation> getAllReservations(){
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationList;
    }

    public List<Reservation> getAllReservationsByUserId(Long user_id){
        Optional<User> user = userRepository.findById(user_id);
        user.orElseThrow(() -> new SpringHotelManagerException("User with the id given not found!!"));

        List<Reservation> reservationList = reservationRepository.findAllByUser(user.get());
        return reservationList;
    }


    public void modifyReservationForCurrentUser(ReservationInfos reservationInfos){

        Optional<Reservation> reservation = reservationRepository.findById(reservationInfos.getReservationId());
        reservation.orElseThrow(() -> new SpringHotelManagerException("Reservation with the same id not found!!"));

        Optional<Room> room = roomRepository.findById(reservationInfos.getRoomId());
        room.orElseThrow(() -> new SpringHotelManagerException("No room with the id given!!"));

        if(!checkCurrentUserId(reservationInfos.getUserId())){
            throw new SpringHotelManagerException("Access denied for current user!!");
        }

        reservation.get().setChildrenNumber(reservationInfos.getChildrenNumber());
        reservation.get().setAdultsNumber(reservationInfos.getAdultsNumber());
        reservation.get().setReservationDate(reservationInfos.getReservationDate());
        reservation.get().setReservationCheckInDate(reservationInfos.getReservationCheckInDate());
        reservation.get().setReservationCheckOutDate(reservationInfos.getReservationCheckOutDate());
        reservation.get().setRoom(room.get());


    }


    public void deleteReservationForCurrentUser(ReservationInfos reservationInfos){
        Optional<Reservation> reservation = reservationRepository.findById(reservationInfos.getReservationId());
        reservation.orElseThrow(() -> new SpringHotelManagerException("Reservation with the same id not found!!"));

        Optional<Rent> rent = rentRepository.findByReservation(reservation.get());
        rent.ifPresent(new Consumer<Rent>() {
            @Override
            public void accept(Rent rent) {
                throw new SpringHotelManagerException("Cannot infer functional interface type");
            }
        });

        if(!checkCurrentUserId(reservationInfos.getUserId())){
            throw new SpringHotelManagerException("Access denied for current user!!");
        }

        reservationRepository.delete(reservation.get());

    }

    private boolean checkCurrentUserId(Long id){
        User currentUser = authService.getCurrentUser();

        Optional<User> user = userRepository.findById(id);
        user.orElseThrow(() -> new SpringHotelManagerException("User with the id given not found!!"));

        if(currentUser.getUserEmail().equals(user.get().getUserEmail())){
            return true;
        }
        return false;
    }
}
