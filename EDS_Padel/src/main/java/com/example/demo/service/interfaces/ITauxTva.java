package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.TauxTva;

import java.util.List;

public interface ITauxTva {
    TauxTva saveTauxTva(TauxTva tauxTva);
    TauxTva updateTauxTva(TauxTva tauxTva);
    boolean deleteTauxTva(Long id);
    List<TauxTva> getListTauxTva();
    TauxTva getTauxTvaByIdTauxTva(Long id);
}
