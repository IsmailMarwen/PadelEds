package com.example.demo.service.controller;
import com.example.demo.persistance.entities.*;

import com.example.demo.persistance.helper.ReservationHelper;
import com.example.demo.service.impliments.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebSocketReservationController {

    private final MatchService matchService;
    private final ReservationService reservationService;
    @Autowired
    public RessourceService ressourceService;
    @Autowired
    public MembreService membreService;
    @Autowired
    public CoachService coachService;
    public WebSocketReservationController(MatchService matchService, ReservationService reservationService) {
        this.matchService = matchService;
        this.reservationService = reservationService;
    }
    @MessageMapping("/addReservation")
    @SendTo("/topic/reservations")
    @Transactional
    public List<Reservation> addReservation(ReservationHelper reservationHelper, @Header("idRessource") Long idRessource, @Header("date") String date) {
        Ressource ressource = ressourceService.getRessourceByIdRessource(reservationHelper.getReservation().getRessource().getId());

        MatchDetail matchDetail = reservationHelper.getMatch();

        // Initialize members and coaches lists if they are null


        MatchDetail savedMatch = matchService.saveMatch(matchDetail);

        Reservation res = reservationHelper.getReservation();
        res.setMatch(savedMatch);
        res.setRessource(ressource);

        Reservation savedRes = reservationService.saveReservation(res);

        List<Reservation> reservations = reservationService.getListByRessourceAndDate(idRessource, date);
        reservations.forEach(reservation -> {
            reservation.getRessource().getClub().getActivites().size();
            if (reservation.getMatch() != null) {
                reservation.getMatch().getMembres().size();
                reservation.getMatch().getCoaches().size();
            }
        });

        return reservations;
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
