package com.example.demo.service.controller;
import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.dao.AgentAcceuilRepository;
import com.example.demo.persistance.dao.CoachRepository;
import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.Membre;
import com.example.demo.service.interfaces.IMembre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/membre")
public class MembreController {
    @Autowired
    AdminstarteurRepository adminstarteurRepository;
    @Autowired
    AgentAcceuilRepository agentAcceuilRepository;
    @Autowired
    MembreRepository membreRepository;
    @Autowired
    CoachRepository coachRepository;
    private final IMembre iMembre;
    public MembreController(IMembre iMembre){
        this.iMembre=iMembre;}

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Membre membre) {
        Membre m=iMembre.saveMembre(membre);
        if(adminstarteurRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null || membreRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null || membreRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
        }
        if(adminstarteurRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null || membreRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null || membreRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
        }
        if(adminstarteurRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null || membreRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null || coachRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Membre membre) {
        Membre m=iMembre.updateMembre(membre);
        if(adminstarteurRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null || membreRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null || membreRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
        }
        if(adminstarteurRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null || membreRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null || membreRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
        }
        if(adminstarteurRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null || membreRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null || coachRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }
    @GetMapping("/getAll")
    List<Membre> getAllMembres() {

        return iMembre.getListMembre();
    }

    @GetMapping("/getById/{id}")
    Membre getMembreById(@PathVariable Long id) {

        return iMembre.getMembreByIdMembre(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iMembre.deleteMembre(id);
        return true;
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<Membre> getAllMembreByClub(@PathVariable Long idClub) {

        return iMembre.getListMembreByClub(idClub);
    }
}
