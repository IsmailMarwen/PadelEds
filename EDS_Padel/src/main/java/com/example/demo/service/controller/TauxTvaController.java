package com.example.demo.service.controller;

import com.example.demo.persistance.entities.TauxTva;
import com.example.demo.service.interfaces.ITauxTva;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/tauxTva")
public class TauxTvaController {
    private final ITauxTva iTauxTva;
    public TauxTvaController(ITauxTva iTauxTva){
        this.iTauxTva=iTauxTva;}
    @PostMapping("/add")
    TauxTva save(@RequestBody TauxTva tauxTva) {
        TauxTva a=iTauxTva.saveTauxTva(tauxTva);
        return a ;
    }
    @PutMapping("/update")
    TauxTva update(@RequestBody TauxTva tauxTva) {

        return iTauxTva.updateTauxTva(tauxTva);
    }
    @GetMapping("/getAll")
    List<TauxTva> getAllTauxTvas() {

        return iTauxTva.getListTauxTva();
    }

    @GetMapping("/getById/{id}")
    TauxTva getTauxTvaById(@PathVariable Long id) {

        return iTauxTva.getTauxTvaByIdTauxTva(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iTauxTva.deleteTauxTva(id);
        return true;
    }
}
