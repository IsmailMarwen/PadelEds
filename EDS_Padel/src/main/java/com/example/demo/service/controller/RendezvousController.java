package com.example.demo.service.controller;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.persistance.entities.Rendezvous;
import com.example.demo.service.interfaces.IRendezvous;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/rdv")
public class RendezvousController {
    @Autowired
    IRendezvous rendezvousservice;

    @RequestMapping(method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    Rendezvous save(@RequestBody Rendezvous rendezvous) {
        System.out.println("*******save ***********");
        Rendezvous r=rendezvousservice.saveRendezvous(rendezvous);
        System.out.println("*******"+r.getDateRDV()+" "+r.getHeureRDV());
        return r ;
    }


    @GetMapping("/{id}")
    Rendezvous getRendezvousById(@PathVariable Long id) {

        return rendezvousservice.getRendezvous(id);
    }

    @GetMapping("/quantity")
    int getQuantityRendezvous() {

        return rendezvousservice.getQuantityOfRendezvous();
    }
/*
    @GetMapping("/RendezvousById/{id}")
    Rendezvous getRendezvousById(@PathVariable Long id) {

        return rendezvousservice.getRendezvousById(id);
    }*/

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        rendezvousservice.deleteRendezvous(id);
        return true;
    }
    @GetMapping("/all")
    List<Rendezvous> getAllRndv(){
        return  rendezvousservice.getListRendezvous();
    }

    @GetMapping("/patient/{id}")
    List<Rendezvous> getAll(@PathVariable Long id){
        return  rendezvousservice.getRendezvousByPatient(id);
    }
    @PutMapping("/update")
    Rendezvous updateMedecin(@RequestBody Rendezvous rendezvous) {

        return rendezvousservice.updateRendezvous(rendezvous);
    }
}
