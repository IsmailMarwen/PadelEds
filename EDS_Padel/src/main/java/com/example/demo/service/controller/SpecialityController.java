package com.example.demo.service.controller;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.example.demo.persistance.entities.Speciality;
import com.example.demo.service.interfaces.ISpeciality;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/speciality")
public class SpecialityController {
    @Autowired
    ISpeciality specialityservice;

    @RequestMapping(method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    Speciality save(@RequestBody Speciality speciality) {
        System.out.println("*******save ***********");
        Speciality s=specialityservice.saveSpeciality(speciality);
        System.out.println("*******"+s.getSpecialityName());
        return s ;
    }


    @GetMapping("/{id}")
    Speciality getSpecialityById(@PathVariable Long id) {

        return specialityservice.getSpeciality(id);
    }


    @GetMapping("/SpecialityByName/{name}")
    Speciality getSpecialityByName(@PathVariable String name) {

        return specialityservice.findSpecialityByName(name);
    }

    @GetMapping("/quantity")
    int getQuantitySpeciality() {

        return specialityservice.getQuantityOfSpeciality();
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        specialityservice.deleteSpeciality(id);
        return true;
    }
    @GetMapping("/all")
    List<Speciality> getAll(){
        return specialityservice.getAll();
    }
}

