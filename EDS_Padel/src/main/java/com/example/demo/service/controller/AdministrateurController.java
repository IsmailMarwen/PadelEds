package com.example.demo.service.controller;
import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.dao.AgentAcceuilRepository;
import com.example.demo.persistance.dao.CoachRepository;
import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.persistance.entities.Membre;
import com.example.demo.service.interfaces.IAdministrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/administrateur")
public class AdministrateurController {
    @Autowired
    AdminstarteurRepository adminstarteurRepository;
    @Autowired
    AgentAcceuilRepository agentAcceuilRepository;
    @Autowired
    MembreRepository membreRepository;
    @Autowired
    CoachRepository coachRepository;
    private final IAdministrateur iAdministrateur;
    public AdministrateurController(IAdministrateur iAdministrateur){
        this.iAdministrateur=iAdministrateur;
    }
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Administrateur a) {
        if(adminstarteurRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || membreRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || coachRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
        }
        if(adminstarteurRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || membreRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || coachRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
        }
        if(adminstarteurRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || membreRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || coachRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
        }
        Administrateur admin= iAdministrateur.saveAdminstrateur(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Administrateur a) {
        if(adminstarteurRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || membreRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || coachRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
        }
        if(adminstarteurRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || membreRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || coachRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
        }
        if(adminstarteurRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || membreRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || coachRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
        }
        Administrateur admin= iAdministrateur.updateAdminstarteur(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
    }
    @GetMapping("/getAll")
    List<Administrateur> getAllAdminstrateurs() {

        return iAdministrateur.getListAdminstarteur();
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<Administrateur> getAllAdminstrateursByClub(@PathVariable Long idClub) {

        return iAdministrateur.getListAdminstarteurByClub(idClub);
    }

    @GetMapping("/getById/{id}")
    Administrateur getAdminstarteurnById(@PathVariable Long id) {

        return iAdministrateur.getAdminstarteurByIdAdminstarteur(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iAdministrateur.deleteAdminstarteur(id);
        return true;
    }
    @PutMapping("/validateCoach")
    public Coach validateCoach(@RequestBody Coach a) {
        return iAdministrateur.ValidateCompteCoach(a);

    }
    @PutMapping("/validateMembre")
    public Membre validateMembre(@RequestBody Membre a) {
        return iAdministrateur.ValidateCompteMembre(a);

    }


}
