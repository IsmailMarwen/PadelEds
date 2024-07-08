package com.example.demo.service.controller;
import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.dao.AgentAcceuilRepository;
import com.example.demo.persistance.dao.CoachRepository;
import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.AgentAcceuil;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.service.interfaces.ICoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/coach")
public class CoachController {
    @Autowired
    AdminstarteurRepository adminstarteurRepository;
    @Autowired
    AgentAcceuilRepository agentAcceuilRepository;
    @Autowired
    MembreRepository membreRepository;
    @Autowired
    CoachRepository coachRepository;
    private final ICoach iCoach;
    public CoachController(ICoach iCoach){
        this.iCoach=iCoach;}

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Coach coach) {
        Coach m=iCoach.saveCoach(coach);
        if(adminstarteurRepository.findByEmailAndClub(coach.getEmail(),coach.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(coach.getEmail(),coach.getClub())!=null || membreRepository.findByEmailAndClub(coach.getEmail(),coach.getClub())!=null || coachRepository.findByEmailAndClub(coach.getEmail(),coach.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
        }
        if(adminstarteurRepository.findByTelephoneAndClub(coach.getTelephone(),coach.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(coach.getTelephone(),coach.getClub())!=null || membreRepository.findByTelephoneAndClub(coach.getTelephone(),coach.getClub())!=null || coachRepository.findByTelephoneAndClub(coach.getTelephone(),coach.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
        }
        if(adminstarteurRepository.findByUsernameAndClub(coach.getUsername(),coach.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(coach.getUsername(),coach.getClub())!=null || membreRepository.findByUsernameAndClub(coach.getUsername(),coach.getClub())!=null || coachRepository.findByUsernameAndClub(coach.getUsername(),coach.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Coach coach) {
        Coach m=iCoach.updateCoach(coach);
        if(adminstarteurRepository.findByEmailAndClub(coach.getEmail(),coach.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(coach.getEmail(),coach.getClub())!=null || membreRepository.findByEmailAndClub(coach.getEmail(),coach.getClub())!=null || coachRepository.findByEmailAndClub(coach.getEmail(),coach.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
        }
        if(adminstarteurRepository.findByTelephoneAndClub(coach.getTelephone(),coach.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(coach.getTelephone(),coach.getClub())!=null || membreRepository.findByTelephoneAndClub(coach.getTelephone(),coach.getClub())!=null || coachRepository.findByTelephoneAndClub(coach.getTelephone(),coach.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
        }
        if(adminstarteurRepository.findByUsernameAndClub(coach.getUsername(),coach.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(coach.getUsername(),coach.getClub())!=null || membreRepository.findByUsernameAndClub(coach.getUsername(),coach.getClub())!=null || coachRepository.findByUsernameAndClub(coach.getUsername(),coach.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }
    @GetMapping("/getAll")
    List<Coach> getAllCoachs() {

        return iCoach.getListCoach();
    }

    @GetMapping("/getById/{id}")
    Coach getCoachById(@PathVariable Long id) {

        return iCoach.getCoachByIdCoach(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iCoach.deleteCoach(id);
        return true;
    }
    @GetMapping("/nom")
    public List<Coach> getCoachsByNom(@RequestParam String nom) {
        return iCoach.getCoachsByNom(nom);
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<Coach> getAllCoachByClub(@PathVariable Long idClub) {

        return iCoach.getListCoachByClub(idClub);
    }
}
