package com.example.demo.service.controller;

import com.example.demo.persistance.entities.TypeDepense;
import com.example.demo.service.interfaces.ITypeDepense;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/typeDepense")
public class TypeDepenseController {
    private final ITypeDepense iTypeDepense;
    public TypeDepenseController(ITypeDepense iTypeDepense){
        this.iTypeDepense=iTypeDepense;}
    @PostMapping("/add")
    TypeDepense save(@RequestBody TypeDepense typeDepense) {
        TypeDepense a=iTypeDepense.saveTypeDepense(typeDepense);
        return a ;
    }
    @PutMapping("/update")
    TypeDepense update(@RequestBody TypeDepense typeDepense) {

        return iTypeDepense.updateTypeDepense(typeDepense);
    }
    @GetMapping("/getAll")
    List<TypeDepense> getAllTypeDepenses() {

        return iTypeDepense.getListTypeDepense();
    }

    @GetMapping("/getById/{id}")
    TypeDepense getTypeDepenseById(@PathVariable Long id) {

        return iTypeDepense.getTypeDepenseByIdTypeDepense(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iTypeDepense.deleteTypeDepense(id);
        return true;
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<TypeDepense> getAllTypeDepenseByClub(@PathVariable Long idClub) {

        return iTypeDepense.getListTypeDepenseByClub(idClub);
    }
}
