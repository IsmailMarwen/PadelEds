package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Ressource;
import com.example.demo.service.interfaces.IRessource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/ressource")
public class RessourceController {
    private final IRessource iRessource;
    public RessourceController(IRessource iRessource){
        this.iRessource=iRessource;}
    @PostMapping("/add")
    Ressource save(@RequestBody Ressource ressource) {
        Ressource a=iRessource.saveRessource(ressource);
        return a ;
    }
    @PutMapping("/update")
    Ressource update(@RequestBody Ressource ressource) {

        return iRessource.updateRessource(ressource);
    }
    @GetMapping("/getAll")
    List<Ressource> getAllRessources() {

        return iRessource.getListRessource();
    }

    @GetMapping("/getById/{id}")
    Ressource getRessourceById(@PathVariable Long id) {

        return iRessource.getRessourceByIdRessource(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iRessource.deleteRessource(id);
        return true;
    }
}
