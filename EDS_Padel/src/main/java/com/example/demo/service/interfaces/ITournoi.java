package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Tournoi;

import java.util.List;

public interface ITournoi {
    Tournoi saveTournoi(Tournoi tournoi);
    Tournoi updateTournoi(Tournoi tournoi);
    boolean deleteTournoi(Long id);
    List<Tournoi> getListTournoi();
    Tournoi getTournoiByIdTournoi(Long id);
}
