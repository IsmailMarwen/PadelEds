package com.example.demo.service.controller;

import com.example.demo.persistance.dao.TournoiRepository;
import com.example.demo.persistance.entities.Tournoi;
import com.example.demo.service.interfaces.ITournoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/Tournoi")
public class TournoiController {
    @Autowired
    private TournoiRepository tournoiRepository;
    private final ITournoi iTournoi;
    public TournoiController(ITournoi iTournoi){
        this.iTournoi=iTournoi;}
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Tournoi tournoi) {
        Tournoi a=iTournoi.saveTournoi(tournoi);
        if(tournoiRepository.findByNomTournoiAndClub(tournoi.getNomTournoi(),tournoi.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tournoi déjà existe");

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Tournoi tournoi) {
        Tournoi a=iTournoi.updateTournoi(tournoi);
        if(tournoiRepository.findByNomTournoiAndClub(tournoi.getNomTournoi(),tournoi.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tournoi déjà existe");

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }
    @GetMapping("/getAll")
    List<Tournoi> getAllTournois() {

        return iTournoi.getListTournoi();
    }

    @GetMapping("/getById/{id}")
    Tournoi getAdminstarteurnById(@PathVariable Long id) {

        return iTournoi.getTournoiByIdTournoi(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iTournoi.deleteTournoi(id);
        return true;
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<Tournoi> getAllTournoiByClub(@PathVariable Long idClub) {

        return iTournoi.getListTournoiByClub(idClub);
    }
}
