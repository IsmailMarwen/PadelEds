package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Reservation;

import java.util.List;

public interface IReservation {
    Reservation saveReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    boolean deleteReservation(Long id);
    List<Reservation> getListReservation();
    Reservation getReservationByIdReservation(Long id);
    List<Reservation> getListReservationByClub(Long idClub);
    List<Reservation> getListReservationByRessource(Long idRessource);
}
