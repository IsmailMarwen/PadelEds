package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Reservation;
import com.example.demo.persistance.entities.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> getAllByClub(Club club);
    List<Reservation> getAllByRessource(Ressource ressource);
}
