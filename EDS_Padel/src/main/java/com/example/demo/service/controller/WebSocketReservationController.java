package com.example.demo.service.controller;
import com.example.demo.persistance.entities.MatchDetail;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public class WebSocketReservationController {

    private final MatchService matchService;
    private final ReservationService reservationService;
    public WebSocketReservationController(MatchService matchService, ReservationService reservationService) {
        this.matchService = matchService;
        this.reservationService = reservationService;
    }
    @MessageMapping("/addReservation")
    @SendTo("/topic/reservations")
    @Transactional
    public List<Reservation> addReservation(ReservationHelper reservationHelper, @Header("idRessource") Long idRessource, @Header("date") String date) {
        // Save the match first to ensure it's available
        MatchDetail savedMatch = matchService.saveMatch(reservationHelper.getMatch());
        System.out.println(savedMatch.toString());
        // Set the match in the reservation and then save the reservation
        Reservation res = reservationHelper.getReservation();
        res.setMatch(savedMatch);
        Reservation savedRes = reservationService.saveReservation(res);

        // Ensure that the match is set correctly in the saved reservation
        savedRes.setMatch(savedMatch);
        reservationService.saveReservation(savedRes);

        // Retrieve the list of reservations
        List<Reservation> reservations = reservationService.getListByRessourceAndDate(idRessource, date);
        
        // Force initialization of lazy-loaded collections
          reservations.forEach(reservation -> {
            reservation.getRessource().getClub().getActivites().size();
           
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
