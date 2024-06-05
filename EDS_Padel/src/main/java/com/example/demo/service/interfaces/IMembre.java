package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Membre;

import java.util.List;

public interface IMembre {

    Membre saveMembre(Membre membre);
    Membre updateMembre(Membre membre);
    boolean deleteMembre(Long id);
    List<Membre> getListMembre();
    Membre getMembreByIdMembre(Long id);
}
