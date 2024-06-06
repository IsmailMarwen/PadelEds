package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.service.interfaces.IAdministrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/administrateur")
public class AdministrateurController {
    @Autowired
    public IAdministrateur iAdministrateur;
    @PostMapping("/add")
    Administrateur save(@RequestBody Administrateur administrateur) {
        Administrateur a= iAdministrateur.saveAdminstrateur(administrateur);
        return a ;
    }
    @PutMapping("/update")
    Administrateur update(@RequestBody Administrateur administrateur) {

        return iAdministrateur.updateAdminstarteur(administrateur);
    }
    @GetMapping("/getAll")
    List<Administrateur> getAllAdminstrateurs() {

        return iAdministrateur.getListAdminstarteur();
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
}
