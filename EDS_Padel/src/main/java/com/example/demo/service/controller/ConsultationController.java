package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Consultation;
import com.example.demo.service.interfaces.IConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/consultation")
public class ConsultationController {
    @Autowired
    IConsultation iConsultation;

    @RequestMapping(method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    Consultation save(@RequestBody Consultation consultation) {
        System.out.println("*******save ***********");
        Consultation c=iConsultation.saveConsultation(consultation);
        System.out.println("*******"+c.getRecapCons());
        return c ;
    }


    @GetMapping("/{id}")
    Consultation getConsultationById(@PathVariable Long id) {

        return iConsultation.getConsultation(id);
    }

    @GetMapping("/quantity")
    int getQuantityConsultation() {

        return iConsultation.getQuantityOfConsultation();
    }

   /* @GetMapping("/consultationById/{id}")
    Consultation getConsultationById(@PathVariable Long id) {

        return iConsultation.getConsultationById(id);
    }*/

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iConsultation.deleteConsultation(id);
        return true;
    }
}
