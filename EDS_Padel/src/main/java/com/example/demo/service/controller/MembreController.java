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
        if(adminstarteurRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null || membreRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null || membreRepository.findByEmailAndClub(membre.getEmail(),membre.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
        }
        if(adminstarteurRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null || membreRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null || membreRepository.findByTelephoneAndClub(membre.getTelephone(),membre.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
        }
        if(adminstarteurRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null || membreRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null || coachRepository.findByUsernameAndClub(membre.getUsername(),membre.getClub())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
        }
        Membre m=iMembre.saveMembre(membre);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }
    @PutMapping("/update")
public ResponseEntity<?> update(@RequestBody Membre membre) {
    Membre existingMembre = membreRepository.findById(membre.getIdUtilisateur()).orElse(null);

    if (existingMembre == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Membre non trouvé");
    }

    if ((adminstarteurRepository.findByEmailAndClub(membre.getEmail(), membre.getClub()) != null && !adminstarteurRepository.findByEmailAndClub(membre.getEmail(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur())) || 
        (agentAcceuilRepository.findByEmailAndClub(membre.getEmail(), membre.getClub()) != null && !agentAcceuilRepository.findByEmailAndClub(membre.getEmail(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur())) || 
        (membreRepository.findByEmailAndClub(membre.getEmail(), membre.getClub()) != null && !membreRepository.findByEmailAndClub(membre.getEmail(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur())) || 
        (coachRepository.findByEmailAndClub(membre.getEmail(), membre.getClub()) != null && !coachRepository.findByEmailAndClub(membre.getEmail(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur()))) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà exist");
    }

    if ((adminstarteurRepository.findByTelephoneAndClub(membre.getTelephone(), membre.getClub()) != null && !adminstarteurRepository.findByTelephoneAndClub(membre.getTelephone(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur())) || 
        (agentAcceuilRepository.findByTelephoneAndClub(membre.getTelephone(), membre.getClub()) != null && !agentAcceuilRepository.findByTelephoneAndClub(membre.getTelephone(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur())) || 
        (membreRepository.findByTelephoneAndClub(membre.getTelephone(), membre.getClub()) != null && !membreRepository.findByTelephoneAndClub(membre.getTelephone(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur())) || 
        (coachRepository.findByTelephoneAndClub(membre.getTelephone(), membre.getClub()) != null && !coachRepository.findByTelephoneAndClub(membre.getTelephone(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur()))) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà exist");
    }

    if ((adminstarteurRepository.findByUsernameAndClub(membre.getUsername(), membre.getClub()) != null && !adminstarteurRepository.findByUsernameAndClub(membre.getUsername(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur())) || 
        (agentAcceuilRepository.findByUsernameAndClub(membre.getUsername(), membre.getClub()) != null && !agentAcceuilRepository.findByUsernameAndClub(membre.getUsername(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur())) || 
        (membreRepository.findByUsernameAndClub(membre.getUsername(), membre.getClub()) != null && !membreRepository.findByUsernameAndClub(membre.getUsername(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur())) || 
        (coachRepository.findByUsernameAndClub(membre.getUsername(), membre.getClub()) != null && !coachRepository.findByUsernameAndClub(membre.getUsername(), membre.getClub()).getIdUtilisateur().equals(membre.getIdUtilisateur()))) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username déjà exist");
    }

    Membre updatedMembre = iMembre.updateMembre(membre);
    return ResponseEntity.status(HttpStatus.OK).body(updatedMembre);
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
    @GetMapping("/getAllValidateByClub/{idClub}")
    List<Membre> getAllMembreValidateByClub(@PathVariable Long idClub) {

        return iMembre.getListMembreValidateByClub(idClub);
    }
    @GetMapping("/getAllNotValidateByClub/{idClub}")
    List<Membre> getAllMembreNotValidateByClub(@PathVariable Long idClub) {

        return iMembre.getListMembreNotValidateByClub(idClub);
    }
    @PostMapping ("/annulerCompte")
    boolean AnnulerCompte(@RequestBody Membre membre) {
        iMembre.AnnulerCompte(membre);
        return true;
    }
}
