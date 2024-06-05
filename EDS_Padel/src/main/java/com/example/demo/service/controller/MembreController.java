package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Membre;
import com.example.demo.service.interfaces.IMembre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/membre")
public class MembreController {
    @Autowired
    public IMembre iMembre;
    @PostMapping("/add")
    Membre save(@RequestBody Membre membre) {
        Membre m=iMembre.saveMembre(membre);
        return m ;
    }
    @PutMapping("/update")
    Membre update(@RequestBody Membre membre) {

        return iMembre.updateMembre(membre);
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
}
