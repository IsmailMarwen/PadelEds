package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Adminstrateur;
import com.example.demo.service.interfaces.IAdminstrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/adminstrateur")
public class AdminstrateurController {
    @Autowired
    public IAdminstrateur iAdminstrateur;
    @PostMapping("/add")
    Adminstrateur save(@RequestBody Adminstrateur adminstrateur) {
        Adminstrateur a=iAdminstrateur.saveAdminstrateur(adminstrateur);
        return a ;
    }
    @PutMapping("/update")
    Adminstrateur update(@RequestBody Adminstrateur adminstrateur) {

        return iAdminstrateur.updateAdminstarteur(adminstrateur);
    }
    @GetMapping("/getAll")
    List<Adminstrateur> getAllAdminstrateurs() {

        return iAdminstrateur.getListAdminstarteur();
    }

    @GetMapping("/getById/{id}")
    Adminstrateur getAdminstarteurnById(@PathVariable Long id) {

        return iAdminstrateur.getAdminstarteurByIdAdminstarteur(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iAdminstrateur.deleteAdminstarteur(id);
        return true;
    }
}
