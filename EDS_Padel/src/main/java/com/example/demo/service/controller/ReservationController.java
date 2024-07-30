package com.example.demo.service.controller;

import com.example.demo.persistance.entities.MatchDetail;
import com.example.demo.persistance.entities.Reservation;
import com.example.demo.service.interfaces.IMatch;
import com.example.demo.service.interfaces.IReservation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final IReservation iReservation;
    private  final IMatch iMatch;
    public ReservationController(IReservation iReservation,IMatch iMatch){
        this.iReservation=iReservation;
        this.iMatch=iMatch;
    }
    @PostMapping("/add")
    Reservation save(@RequestBody Reservation reservation) {
        Reservation a=iReservation.saveReservation(reservation);
        return a ;
    }
    @PutMapping("/update")
    Reservation update(@RequestBody Reservation reservation) {

        return iReservation.updateReservation(reservation);
    }
    @GetMapping("/getAll")
    List<Reservation> getAllReservations() {

        return iReservation.getListReservation();
    }
    @GetMapping("/getAllMatches")
    List<MatchDetail> getAllMatches() {

        return iMatch.getAll();
    }
    @GetMapping("/getById/{id}")
    Reservation getAdminstarteurnById(@PathVariable Long id) {

        return iReservation.getReservationByIdReservation(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iReservation.deleteReservation(id);
        return true;
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<Reservation> getAllReservationByClub(@PathVariable Long idClub) {

        return iReservation.getListReservationByClub(idClub);
    }
    @GetMapping("/getAllByClub/{idRessource}/{date}")
    List<Reservation> getAllReservationByRessourceAndDate(@PathVariable Long idRessource,@PathVariable String date) {
        return iReservation.getListByRessourceAndDate(idRessource,date);
    }
}
