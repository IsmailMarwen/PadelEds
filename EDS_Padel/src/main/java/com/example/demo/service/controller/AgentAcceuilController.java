package com.example.demo.service.controller;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.dao.AgentAcceuilRepository;
import com.example.demo.persistance.dao.CoachRepository;
import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.AgentAcceuil;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.persistance.entities.Membre;
import com.example.demo.service.interfaces.IAgentAcceuil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/agentAcceuil")
public class AgentAcceuilController {
    @Autowired
    AdminstarteurRepository adminstarteurRepository;
    @Autowired
    AgentAcceuilRepository agentAcceuilRepository;
    @Autowired
    MembreRepository membreRepository;
    @Autowired
    CoachRepository coachRepository;
    private final IAgentAcceuil iAgentAcceuil;
    public AgentAcceuilController(IAgentAcceuil iAgentAcceuil){
        this.iAgentAcceuil=iAgentAcceuil;}

     @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody AgentAcceuil a) {
        if(adminstarteurRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || membreRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || coachRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
        }
        if(adminstarteurRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || membreRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || coachRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
        }
        if(adminstarteurRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || membreRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || coachRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
        }
        AgentAcceuil m=iAgentAcceuil.saveAgentAcceuil(a);

        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }
    @PutMapping("/update")
public ResponseEntity<?> update(@RequestBody AgentAcceuil a) {
    AgentAcceuil existingAgentAcceuil = agentAcceuilRepository.findById(a.getIdUtilisateur()).orElse(null);

    if (existingAgentAcceuil == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AgentAcceuil non trouvé");
    }

    if ((adminstarteurRepository.findByEmailAndClub(a.getEmail(), a.getClub()) != null && !adminstarteurRepository.findByEmailAndClub(a.getEmail(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur())) || 
        (agentAcceuilRepository.findByEmailAndClub(a.getEmail(), a.getClub()) != null && !agentAcceuilRepository.findByEmailAndClub(a.getEmail(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur())) || 
        (membreRepository.findByEmailAndClub(a.getEmail(), a.getClub()) != null && !membreRepository.findByEmailAndClub(a.getEmail(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur())) || 
        (coachRepository.findByEmailAndClub(a.getEmail(), a.getClub()) != null && !coachRepository.findByEmailAndClub(a.getEmail(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur()))) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
    }

    if ((adminstarteurRepository.findByTelephoneAndClub(a.getTelephone(), a.getClub()) != null && !adminstarteurRepository.findByTelephoneAndClub(a.getTelephone(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur())) || 
        (agentAcceuilRepository.findByTelephoneAndClub(a.getTelephone(), a.getClub()) != null && !agentAcceuilRepository.findByTelephoneAndClub(a.getTelephone(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur())) || 
        (membreRepository.findByTelephoneAndClub(a.getTelephone(), a.getClub()) != null && !membreRepository.findByTelephoneAndClub(a.getTelephone(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur())) || 
        (coachRepository.findByTelephoneAndClub(a.getTelephone(), a.getClub()) != null && !coachRepository.findByTelephoneAndClub(a.getTelephone(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur()))) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
    }

    if ((adminstarteurRepository.findByUsernameAndClub(a.getUsername(), a.getClub()) != null && !adminstarteurRepository.findByUsernameAndClub(a.getUsername(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur())) || 
        (agentAcceuilRepository.findByUsernameAndClub(a.getUsername(), a.getClub()) != null && !agentAcceuilRepository.findByUsernameAndClub(a.getUsername(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur())) || 
        (membreRepository.findByUsernameAndClub(a.getUsername(), a.getClub()) != null && !membreRepository.findByUsernameAndClub(a.getUsername(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur())) || 
        (coachRepository.findByUsernameAndClub(a.getUsername(), a.getClub()) != null && !coachRepository.findByUsernameAndClub(a.getUsername(), a.getClub()).getIdUtilisateur().equals(a.getIdUtilisateur()))) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
    }

    AgentAcceuil updatedAgentAcceuil = iAgentAcceuil.updateAgentAcceuil(a);
    return ResponseEntity.status(HttpStatus.OK).body(updatedAgentAcceuil);
}
    @GetMapping("/getAll")
    List<AgentAcceuil> getAllAgentAcceuils() {

        return iAgentAcceuil.getListAgentAcceuil();
    }

    @GetMapping("/getById/{id}")
    AgentAcceuil getAgentAcceuilById(@PathVariable Long id) {

        return iAgentAcceuil.getAgentAcceuilByIdAgentAcceuil(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iAgentAcceuil.deleteAgentAcceuil(id);
        return true;
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<AgentAcceuil> getAllAgentAcceuilByClub(@PathVariable Long idClub) {

        return iAgentAcceuil.getListAgentAcceuilByClub(idClub);
    }
    @PutMapping("/validateCoach")
    public Coach validateCoach(@RequestBody Coach a) {
        return iAgentAcceuil.ValidateCompteCoach(a);

    }
    @PutMapping("/validateMembre")
    public Membre validateMembre(@RequestBody Membre a) {
        return iAgentAcceuil.ValidateCompteMembre(a);

    }
}
