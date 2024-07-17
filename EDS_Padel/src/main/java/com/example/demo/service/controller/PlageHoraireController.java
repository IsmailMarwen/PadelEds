package com.example.demo.service.controller;

import com.example.demo.persistance.entities.PlageHoraire;
import com.example.demo.service.interfaces.IPlageHoraire;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/plageHoraire")
public class PlageHoraireController {
    private final IPlageHoraire iPlageHoraire;
    public PlageHoraireController(IPlageHoraire iPlageHoraire){
        this.iPlageHoraire=iPlageHoraire;}
    @PostMapping("/add")
    PlageHoraire save(@RequestBody PlageHoraire plageHoraire) {
        PlageHoraire a=iPlageHoraire.savePlageHoraire(plageHoraire);
        return a ;
    }
    @PutMapping("/update")
    PlageHoraire update(@RequestBody PlageHoraire plageHoraire) {

        return iPlageHoraire.updatePlageHoraire(plageHoraire);
    }
    @GetMapping("/getAll")
    List<PlageHoraire> getAllPlageHoraires() {

        return iPlageHoraire.getListPlageHoraire();
    }

    @GetMapping("/getById/{id}")
    PlageHoraire getAdminstarteurnById(@PathVariable Long id) {

        return iPlageHoraire.getPlageHoraireByIdPlageHoraire(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iPlageHoraire.deletePlageHoraire(id);
        return true;
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<PlageHoraire> getAllPlageHoraireByClub(@PathVariable Long idClub) {

        return iPlageHoraire.getListPlageHoraireByClub(idClub);
    }
}
