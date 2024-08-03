package com.example.demo.service.controller;
import com.example.demo.persistance.entities.*;

import com.example.demo.persistance.helper.ReservationHelper;
import com.example.demo.service.impliments.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

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
        MatchDetail match = reservationHelper.getMatch();
        match.setReservation(reservationHelper.getReservation());  // Set the reservation in the match
        MatchDetail savedMatch = matchService.saveMatch(match);

        Reservation res = reservationHelper.getReservation();
        res.setMatch(savedMatch);
        res.setRessource(ressource);
        Reservation savedRes = reservationService.saveReservation(res);

// Ensure the match is updated with the saved reservation
        savedMatch.setReservation(savedRes);
        matchService.saveMatch(savedMatch);

// Optionally, you can save the reservation again to ensure consistency
        savedRes.setMatch(savedMatch);
        reservationService.saveReservation(savedRes);
        List<Reservation> reservations = reservationService.getListByRessourceAndDate(idRessource, date);
        reservations.forEach(reservation -> {
            if (reservation.getMatch() != null) {
                if (reservation.getMatch().getMembres() != null) {
                    List<Membre> membres = reservation.getMatch().getMembres().stream()
                            .map(membre -> {
                                Membre fullMembre = membreService.getMembreByIdMembre(membre.getIdUtilisateur());
                                Hibernate.initialize(fullMembre.getTypeAbonnements());
                                Hibernate.initialize(fullMembre.getReservations());
                                return fullMembre;
                            })
                            .collect(Collectors.toList());
                    reservation.getMatch().setMembres(membres);
                }
                if (reservation.getMatch().getCoaches() != null) {
                    List<Coach> coaches = reservation.getMatch().getCoaches().stream()
                            .map(coach -> coachService.getCoachByIdCoach(coach.getIdUtilisateur()))
                            .collect(Collectors.toList());
                    reservation.getMatch().setCoaches(coaches);
                }
            }
            Hibernate.initialize(reservation.getRessource().getClub().getActivites()); // Initialize activites
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