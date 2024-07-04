package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.service.interfaces.IAdministrateur;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/administrateur")
public class AdministrateurController {

    private final IAdministrateur iAdministrateur;
    public AdministrateurController(IAdministrateur iAdministrateur){
        this.iAdministrateur=iAdministrateur;
    }
    @PostMapping("/add")
    Administrateur save(@RequestBody Administrateur a) {
        iAdministrateur.saveAdminstrateur(a);
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
}
