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

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebSocketReservationController {

    private final MatchService matchService;
    private final ReservationService reservationService;
    private final EmailAsyncService emailAsyncService;

    @Autowired
    public RessourceService ressourceService;
    @Autowired
    public MembreService membreService;
    @Autowired
    public CoachService coachService;
    @Autowired
    private EmailService emailService;
    String getColorButton(String theme){
        if(theme.equals("theme1")){
            return "#00C3F9";
        }
        if(theme.equals("theme2")){
            return "#91969E";
        }
        if(theme.equals("theme3")){
            return "#DB5363";
        }
        if(theme.equals("theme4")){
            return "#EA6A12";
        }
        if(theme.equals("theme5")){
            return "#E586B3";
        }
        if(theme.equals("theme6")){
            return "#079aa2";
        }
        return null;
    }
    public WebSocketReservationController(MatchService matchService, ReservationService reservationService,EmailAsyncService emailAsyncService) {
        this.matchService = matchService;
        this.reservationService = reservationService;
        this.emailAsyncService=emailAsyncService;
    }
    @MessageMapping("/addReservation")
    @SendTo("/topic/reservations")
    @Transactional
    public List<Reservation> addReservation(ReservationHelper reservationHelper, @Header("idRessource") Long idRessource, @Header("date") String date) {
        Ressource ressource = ressourceService.getRessourceByIdRessource(reservationHelper.getReservation().getRessource().getId());
        MatchDetail match = reservationHelper.getMatch();
        match.setReservation(reservationHelper.getReservation());
        MatchDetail savedMatch = matchService.saveMatch(match);

        Reservation res = reservationHelper.getReservation();
        res.setMatch(savedMatch);
        res.setRessource(ressource);
        Reservation savedRes = reservationService.saveReservation(res);

        savedMatch.setReservation(savedRes);
        matchService.saveMatch(savedMatch);

        savedRes.setMatch(savedMatch);
        reservationService.saveReservation(savedRes);

        Membre m = membreService.getMembreByIdMembre(savedRes.getMembre().getIdUtilisateur());
        AppWeb appWeb = ressource.getClub().getAppWeb();
        String buttonColor = appWeb != null ? getColorButton(appWeb.getCouleurAppWeb()) : "#000000";
        List<Membre> membresEquivalents = membreService.getListMembresEquiv(m.getNiveauPadel());
        membresEquivalents.removeIf(membre -> membre.getIdUtilisateur().equals(m.getIdUtilisateur()));

        if (savedMatch.getMembres().size() < ressource.getCapacite()) {
            String subject = "Participation à un match";
            String htmlBody = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; margin: 0; padding: 0; }" +
                    ".container { padding: 20px; }" +
                    "h1 { color: #333; }" +
                    "p { font-size: 16px; color: #555; }" +
                    ".logo { width: 50px; margin-bottom: 20px; vertical-align: middle; }" +
                    ".club-name { display: inline-block; vertical-align: middle; margin-left: 10px; font-size: 24px; color: #333; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<div>" +
                    "<img src='" + appWeb.getLogoAppWeb() + "' alt='Logo' class='logo'>" +
                    "<h4 class='club-name'>" + ressource.getClub().getNomClub() + "</h4>" +
                    "</div>" +
                    "<h1>Bonjour,</h1>" +
                    "<p>Tu peut participe à un match dans le " + ressource.getLibelle() + " avec le membre " + m.getNom() + " " + m.getPrenom() + " à partir de " + savedRes.getHeureDebut() + "h jusqu'à " + savedRes.getHeureFin() + "h" + ".</p>" +
                    "<a href='http://localhost:4200/calendar' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Participe</a>" +
                    "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            // Envoyer les emails de manière asynchrone
            emailAsyncService.sendEmails(membresEquivalents, subject, htmlBody);
        }

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
            Hibernate.initialize(reservation.getRessource().getClub().getActivites());
        });
        return reservations;
    }

    @MessageMapping("/updateReservation")
    @SendTo("/topic/reservations")
    public List<Reservation> updateReservation(ReservationHelper reservationHelper, @Header("idRessource") Long idRessource, @Header("date") String date) {
        Ressource ressource = ressourceService.getRessourceByIdRessource(reservationHelper.getReservation().getRessource().getId());
        MatchDetail match = reservationHelper.getMatch();
        match.setReservation(reservationHelper.getReservation());
        MatchDetail savedMatch = matchService.updateMatch(match);

        Reservation res = reservationHelper.getReservation();
        res.setMatch(savedMatch);
        res.setRessource(ressource);
        Reservation savedRes = reservationService.updateReservation(res);

        savedMatch.setReservation(savedRes);
        matchService.updateMatch(savedMatch);

        savedRes.setMatch(savedMatch);
        reservationService.updateReservation(savedRes);

        Membre m = membreService.getMembreByIdMembre(savedRes.getMembre().getIdUtilisateur());
        AppWeb appWeb = ressource.getClub().getAppWeb();
        String buttonColor = appWeb != null ? getColorButton(appWeb.getCouleurAppWeb()) : "#000000";
        List<Membre> membresEquivalents = membreService.getListMembresEquiv(m.getNiveauPadel());
        membresEquivalents.removeIf(membre -> membre.getIdUtilisateur().equals(m.getIdUtilisateur()));

        if (savedMatch.getMembres().size() < ressource.getCapacite()) {
            String subject = "Mise à jour de la participation à un match";
            String htmlBody = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; margin: 0; padding: 0; }" +
                    ".container { padding: 20px; }" +
                    "h1 { color: #333; }" +
                    "p { font-size: 16px; color: #555; }" +
                    ".logo { width: 50px; margin-bottom: 20px; vertical-align: middle; }" +
                    ".club-name { display: inline-block; vertical-align: middle; margin-left: 10px; font-size: 24px; color: #333; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<div>" +
                    "<img src='" + appWeb.getLogoAppWeb() + "' alt='Logo' class='logo'>" +
                    "<h4 class='club-name'>" + ressource.getClub().getNomClub() + "</h4>" +
                    "</div>" +
                    "<h1>Bonjour,</h1>" +
                    "<p>La réservation dans le " + ressource.getLibelle() + " a été mise à jour. " +
                    "Les détails du match sont les suivants : " + m.getNom() + " " + m.getPrenom() + " de " +
                    savedRes.getHeureDebut() + "h à " + savedRes.getHeureFin() + "h" + ".</p>" +
                    "<a href='http://localhost:4200/calendar' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Voir les détails</a>" +
                    "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            // Envoyer les emails de manière asynchrone
            emailAsyncService.sendEmails(membresEquivalents, subject, htmlBody);
        }

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
            Hibernate.initialize(reservation.getRessource().getClub().getActivites());
        });

        return reservations;
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