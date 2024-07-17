package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.PlageHoraire;

import java.util.List;

public interface IPlageHoraire {
    PlageHoraire savePlageHoraire(PlageHoraire plageHoraire);
    PlageHoraire updatePlageHoraire(PlageHoraire plageHoraire);
    boolean deletePlageHoraire(Long id);
    List<PlageHoraire> getListPlageHoraire();
    PlageHoraire getPlageHoraireByIdPlageHoraire(Long id);
    List<PlageHoraire> getListPlageHoraireByClub(Long idClub);

}
