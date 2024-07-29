package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Reservation;
import com.example.demo.service.interfaces.IReservation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final IReservation iReservation;
    public ReservationController(IReservation iReservation){
        this.iReservation=iReservation;}
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
