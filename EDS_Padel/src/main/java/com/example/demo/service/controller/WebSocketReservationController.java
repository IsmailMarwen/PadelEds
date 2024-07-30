package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Reservation;

import com.example.demo.persistance.helper.ReservationHelper;
import com.example.demo.service.impliments.MatchService;
import com.example.demo.service.impliments.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebSocketReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private MatchService matchService;
    @MessageMapping("/addReservation")
    @SendTo("/topic/reservations")
    public List<Reservation> addReservation(ReservationHelper reservation, @Header("idRessource") Long idRessource, @Header("date") String date) {
        matchService.saveMatch(reservation.getMatch());
        Reservation res=reservation.getReservation();
        res.setMatch(reservation.getMatch());
        reservationService.saveReservation(res);
        return reservationService.getListByRessourceAndDate(idRessource, date);
    }

    @MessageMapping("/updateReservation")
    @SendTo("/topic/reservations")
    public List<Reservation> updateReservation(Reservation reservation, @Header("idRessource") Long idRessource, @Header("date") String date) {
        reservationService.updateReservation(reservation);
        return reservationService.getListByRessourceAndDate(idRessource, date);
    }

    @MessageMapping("/deleteReservation")
    @SendTo("/topic/reservations")
    public List<Reservation>  deleteReservation(Long id, @Header("idRessource") Long idRessource, @Header("date") String date) {
        reservationService.deleteReservation(id);
        return reservationService.getListByRessourceAndDate(idRessource, date);
    }

    @MessageMapping("/getReservationsByRessource/{idRessource}")
    @SendTo("/topic/reservations")
    public List<Reservation> getAllReservations(@DestinationVariable("idRessource") Long idRessource) {
        return reservationService.getListReservationByRessource(idRessource);
    }
}
