package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Activite;
import com.example.demo.service.interfaces.IActivite;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/activite")
public class ActiviteController {
    private final IActivite iActivite;
    public ActiviteController(IActivite iActivite){
        this.iActivite=iActivite;}
    @PostMapping("/add")
    Activite save(@RequestBody Activite activite) {
        Activite a=iActivite.saveActivite(activite);
        return a ;
    }
    @PutMapping("/update")
    Activite update(@RequestBody Activite activite) {

        return iActivite.updateActivite(activite);
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
