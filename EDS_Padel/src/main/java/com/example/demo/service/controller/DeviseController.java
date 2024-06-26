package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Devise;
import com.example.demo.service.interfaces.IDevise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/devise")
public class DeviseController {
    private final IDevise iDevise;
    public DeviseController(IDevise iDevise){
        this.iDevise=iDevise;}
    @PostMapping("/add")
    Devise save(@RequestBody Devise devise) {
        Devise a=iDevise.saveDevise(devise);
        return a ;
    }
    @PutMapping("/update")
    Devise update(@RequestBody Devise devise) {

        return iDevise.updateDevise(devise);
    }
    @GetMapping("/getAll")
    List<Devise> getAllDevises() {

        return iDevise.getListDevise();
    }

    @GetMapping("/getById/{id}")
    Devise getDeviseById(@PathVariable Long id) {

        return iDevise.getDeviseByIdDevise(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iDevise.deleteDevise(id);
        return true;
    }
}
