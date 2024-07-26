package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Reservation;

import com.example.demo.service.impliments.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebSocketReservationController {

    @Autowired
    private ReservationService reservationService;

    @MessageMapping("/addReservation")
    @SendTo("/topic/reservations")
    public Reservation addReservation(Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @MessageMapping("/updateReservation")
    @SendTo("/topic/reservations")
    public Reservation updateReservation(Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @MessageMapping("/deleteReservation")
    @SendTo("/topic/reservations")
    public Long deleteReservation(Long id) {
        reservationService.deleteReservation(id);
        return id;
    }

    @MessageMapping("/getReservationsByRessource/{idRessource}")
    @SendTo("/topic/reservations")
    public List<Reservation> getAllReservations(Long idRessource) {
        return reservationService.getListReservationByRessource(idRessource);
    }
}
