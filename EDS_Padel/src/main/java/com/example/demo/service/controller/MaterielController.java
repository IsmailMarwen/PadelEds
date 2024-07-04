package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Materiel;
import com.example.demo.service.interfaces.IMateriel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/Materiel")
public class MaterielController {
    private final IMateriel iMateriel;
    public MaterielController(IMateriel iMateriel){
        this.iMateriel=iMateriel;}
    @PostMapping("/add")
    Materiel save(@RequestBody Materiel Materiel) {
        Materiel a=iMateriel.saveMateriel(Materiel);
        return a ;
    }
    @PutMapping("/update")
    Materiel update(@RequestBody Materiel Materiel) {

        return iMateriel.updateMateriel(Materiel);
    }
    @GetMapping("/getAll")
    List<Materiel> getAllMateriels() {

        return iMateriel.getListMateriel();
    }

    @GetMapping("/getById/{id}")
    Materiel getAdminstarteurnById(@PathVariable Long id) {

        return iMateriel.getMaterielByIdMateriel(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iMateriel.deleteMateriel(id);
        return true;
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<Materiel> getAllMaterielByClub(@PathVariable Long idClub) {

        return iMateriel.getListMaterielByClub(idClub);
    }
}
