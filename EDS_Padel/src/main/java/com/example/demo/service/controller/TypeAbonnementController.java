package com.example.demo.service.controller;

import com.example.demo.persistance.entities.TypeAbonnement;
import com.example.demo.service.interfaces.ITypeAbonnement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/TypeAbonnement")
public class TypeAbonnementController {
    private final ITypeAbonnement iTypeAbonnement;
    public TypeAbonnementController(ITypeAbonnement iTypeAbonnement){
        this.iTypeAbonnement=iTypeAbonnement;}
    @PostMapping("/add")
    TypeAbonnement save(@RequestBody TypeAbonnement typeAbonnement) {
        TypeAbonnement a=iTypeAbonnement.saveTypeAbonnement(typeAbonnement);
        return a ;
    }
    @PutMapping("/update")
    TypeAbonnement update(@RequestBody TypeAbonnement typeAbonnement) {

        return iTypeAbonnement.updateTypeAbonnement(typeAbonnement);
    }
    @GetMapping("/getAll")
    List<TypeAbonnement> getAllTypeAbonnements() {

        return iTypeAbonnement.getListTypeAbonnement();
    }

    @GetMapping("/getById/{id}")
    TypeAbonnement getAdminstarteurnById(@PathVariable Long id) {

        return iTypeAbonnement.getTypeAbonnementByIdTypeAbonnement(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iTypeAbonnement.deleteTypeAbonnement(id);
        return true;
    }


}
