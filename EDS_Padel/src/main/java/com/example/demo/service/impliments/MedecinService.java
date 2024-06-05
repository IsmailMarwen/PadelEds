package com.example.demo.service.impliments;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistance.dao.*;
import com.example.demo.persistance.entities.*;
import com.example.demo.service.interfaces.*;
@Service
public class MedecinService implements IMedecin {
    @Autowired
    public MedecinRepository medecinrepository;

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinrepository.save(medecin);
    }

    @Override
    public Medecin updateMedecin(Medecin medecin) {
        return medecinrepository.saveAndFlush(medecin);
    }

    @Override
    public boolean deleteMedecin(Long id) {
        medecinrepository.deleteById(id);
        return true;
    }

    @Override
    public List<Medecin> getListMedecin() {
        return medecinrepository.findAll();
    }

    @Override
    public Medecin getMedecin(Long id) {
        return medecinrepository.findById(id).get();
    }

    @Override
    public Medecin findMedecinByName(String name) {
        return medecinrepository.findByNom(name);
    }

    @Override
    public int getQuantityOfMedecin() {
        return medecinrepository.getQuantityOfMedecin();
    }

    @Override
    public Medecin getMedecinByIdMedecin(Long id) {
        return null;
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinrepository.findAll();
    }
}
