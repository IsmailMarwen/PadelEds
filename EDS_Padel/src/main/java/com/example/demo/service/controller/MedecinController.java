package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Speciality;
import com.example.demo.service.interfaces.ISpeciality;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.persistance.entities.Medecin;
import com.example.demo.service.interfaces.IMedecin;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/medecin")
public class MedecinController {
    @Autowired
    IMedecin medecinservice;

    @RequestMapping(method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    Medecin save(@RequestBody Medecin medecin) {
        System.out.println("*******save ***********");
        Medecin m=medecinservice.saveMedecin(medecin);
        System.out.println("*******"+m.getEmail());
        return m ;
    }


    @GetMapping("/{id}")
    Medecin getMedecinById(@PathVariable Long id) {

        return medecinservice.getMedecin(id);
    }

    @GetMapping("/quantity")
    int getQuantityMedecin() {

        return medecinservice.getQuantityOfMedecin();
    }

    @GetMapping("/MedecinByName/{name}")
    Medecin getMedecinByName(@PathVariable String name) {

        return medecinservice.findMedecinByName(name);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        medecinservice.deleteMedecin(id);
        return true;
    }
    @GetMapping("/all")
    List<Medecin> getAllMedecins() {
        return medecinservice.getAllMedecins();
    }
    @PutMapping("/update")
    Medecin updateMedecin(@RequestBody Medecin medecin) {
        Medecin updatedMedecin = medecinservice.updateMedecin(medecin);
        return updatedMedecin;
    }
}
