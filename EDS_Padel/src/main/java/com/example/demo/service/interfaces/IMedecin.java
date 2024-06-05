package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Medecin;

import java.util.List;

public interface IMedecin {
    Medecin saveMedecin(Medecin medecin);
    Medecin updateMedecin(Medecin medecin);
    boolean deleteMedecin(Long id);
    List<Medecin> getListMedecin();
    Medecin getMedecin(Long id);
    Medecin findMedecinByName(String name);
    int getQuantityOfMedecin();
    Medecin getMedecinByIdMedecin(Long id);
    List<Medecin> getAllMedecins();
}
