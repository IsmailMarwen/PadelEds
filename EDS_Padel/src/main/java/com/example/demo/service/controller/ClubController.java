package com.example.demo.service.controller;


import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.helper.ClubAppWebRequest;
import com.example.demo.service.interfaces.IClub;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/club")
public class ClubController {
    private final IClub iClub;
    public ClubController(IClub iClub){
        this.iClub=iClub;}
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody ClubAppWebRequest clubAppWebRequest) {
        Club savedClub = iClub.saveClub(clubAppWebRequest);
        if (savedClub == null) {
            // Vérifiez quelle condition a échoué et renvoyez un message approprié
            if (iClub.emailExists(clubAppWebRequest.getClub().getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("L'email du club existe déjà");
            } else if (iClub.nomClubExists(clubAppWebRequest.getClub().getNomClub())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Le nom du club existe déjà");
            } else if (iClub.adresseUrlExists(clubAppWebRequest.getAppWeb().getAdresseUrl())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("L'adresse URL de l'application Web existe déjà");
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClub);
    }
    @PutMapping("/update")
    Club update(@RequestBody Club club) {

        return iClub.updateClub(club);
    }
    @GetMapping("/getAll")
    List<Club> getAllClubs() {

        return iClub.getListClub();
    }

    @GetMapping("/getById/{id}")
    Club getClubById(@PathVariable Long id) {

        return iClub.getClubByIdClub(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iClub.deleteClub(id);
        return true;
    }
    @GetMapping("/proximite")
    public List<Club> getClubsNearLocation(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double distance) {
        return iClub.getClubsNearLocation(latitude, longitude, distance);
    }
    @GetMapping("/ville")
    public List<Club> getClubsByVille(@RequestParam String ville) {
        return iClub.getClubsByVille(ville);
    }
    @GetMapping("/nom")
    public List<Club> getClubsByNom(@RequestParam String nom) {
        return iClub.getClubsByNomClub(nom);
    }
}
