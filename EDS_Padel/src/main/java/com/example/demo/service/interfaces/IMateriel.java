package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Materiel;

import java.util.List;

public interface IMateriel {

    Materiel saveMateriel(Materiel materiel);
    Materiel updateMateriel(Materiel materiel);
    boolean deleteMateriel(Long id);
    List<Materiel> getListMateriel();
    Materiel getMaterielByIdMateriel(Long id);
}
