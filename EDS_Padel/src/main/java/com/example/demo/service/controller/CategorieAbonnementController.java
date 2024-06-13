package com.example.demo.service.controller;

import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.service.interfaces.ICategorieAbonnement;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/api/CategorieAbonnement")
public class CategorieAbonnementController {
    private final ICategorieAbonnement iCategorieAbonnement;
    public CategorieAbonnementController(ICategorieAbonnement iCategorieAbonnement){
        this.iCategorieAbonnement=iCategorieAbonnement;}
    @PostMapping("/add")
    CategorieAbonnement save(@RequestBody CategorieAbonnement CategorieAbonnement) {
        CategorieAbonnement a=iCategorieAbonnement.saveCategorieAbonnement(CategorieAbonnement);
        return a ;
    }
    @PutMapping("/update")
    CategorieAbonnement update(@RequestBody CategorieAbonnement CategorieAbonnement) {

        return iCategorieAbonnement.updateCategorieAbonnement(CategorieAbonnement);
    }
    @GetMapping("/getAll")
    List<CategorieAbonnement> getAllCategorieAbonnements() {

        return iCategorieAbonnement.getListCategorieAbonnement();
    }

    @GetMapping("/getById/{id}")
    CategorieAbonnement getAdminstarteurnById(@PathVariable Long id) {

        return iCategorieAbonnement.getCategorieAbonnementByIdCategorieAbonnement(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iCategorieAbonnement.deleteCategorieAbonnement(id);
        return true;
    }
}
