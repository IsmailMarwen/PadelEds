package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Tournoi;
import com.example.demo.service.interfaces.ITournoi;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/api/Tournoi")
public class TournoiController {
    private final ITournoi iTournoi;
    public TournoiController(ITournoi iTournoi){
        this.iTournoi=iTournoi;}
    @PostMapping("/add")
    Tournoi save(@RequestBody Tournoi Tournoi) {
        Tournoi a=iTournoi.saveTournoi(Tournoi);
        return a ;
    }
    @PutMapping("/update")
    Tournoi update(@RequestBody Tournoi Tournoi) {

        return iTournoi.updateTournoi(Tournoi);
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
}
