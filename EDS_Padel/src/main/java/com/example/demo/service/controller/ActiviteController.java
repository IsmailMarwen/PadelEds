package com.example.demo.service.controller;

import com.example.demo.persistance.dao.ActiviteRepository;
import com.example.demo.persistance.entities.Activite;
import com.example.demo.service.interfaces.IActivite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/activite")
public class ActiviteController {
    @Autowired
    private ActiviteRepository activiteRepository;
    private final IActivite iActivite;
    public ActiviteController(IActivite iActivite){
        this.iActivite=iActivite;}
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Activite activite) {
        Activite a=iActivite.saveActivite(activite);
        if (a == null) {
            if (activiteRepository.existsActiviteByCouleur(activite.getCouleur())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Couleur déjà existe dans une autre activité");
            } else if (activiteRepository.existsActiviteByLibelle(activite.getLibelle())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Activité existe déjà");
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }

    @PutMapping("/update")
  public  ResponseEntity<?> update(@RequestBody Activite activite) {
       Activite a=iActivite.updateActivite(activite);
       if(a==null){
           if (activiteRepository.existsActiviteByCouleur(activite.getCouleur())) {
               return ResponseEntity.status(HttpStatus.CONFLICT).body("Couleur déjà existe dans une autre activité");
           } else if (activiteRepository.existsActiviteByLibelle(activite.getLibelle())) {
               return ResponseEntity.status(HttpStatus.CONFLICT).body("Activité existe déjà");
           }
       }
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }
    @GetMapping("/getAll")
    List<Activite> getAllActivites() {

        return iActivite.getListActivite();
    }

    @GetMapping("/getById/{id}")
    Activite getActiviteById(@PathVariable Long id) {

        return iActivite.getActiviteByIdActivite(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iActivite.deleteActivite(id);
        return true;
    }
}
