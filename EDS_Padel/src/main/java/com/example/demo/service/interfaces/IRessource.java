package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Devise;
import com.example.demo.persistance.entities.Ressource;

import java.util.List;

public interface IRessource {
    Ressource saveRessource(Ressource ressource);
    Ressource updateRessource(Ressource ressource);
    boolean deleteRessource(Long id);
    List<Ressource> getListRessource();
    Ressource getRessourceByIdRessource(Long id);
}
