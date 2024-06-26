package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Activite;

import java.util.List;

public interface IActivite {
    Activite saveActivite(Activite activite);
    Activite updateActivite(Activite activite);
    boolean deleteActivite(Long id);
    List<Activite> getListActivite();
    Activite getActiviteByIdActivite(Long id);
}
