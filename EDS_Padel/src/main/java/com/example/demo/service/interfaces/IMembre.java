package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Banque;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Membre;

import java.util.List;

public interface IMembre {

    Membre saveMembre(Membre membre);
    Membre updateMembre(Membre membre);
    boolean deleteMembre(Long id);
    List<Membre> getListMembre();
    Membre getMembreByIdMembre(Long id);
    List<Membre> getListMembreByClub(Long idClub);

}
