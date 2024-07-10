package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Banque;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.persistance.entities.Utilisateur;

import java.util.List;

public interface ICoach {
    Coach saveCoach(Coach coach);
    Coach updateCoach(Coach coach);
    boolean deleteCoach(Long id);
    List<Coach> getListCoach();
    Coach getCoachByIdCoach(Long id);
    public List<Coach> getCoachsByNom(String nom);
    List<Coach> getListCoachValidateByClub(Long idClub);
    List<Coach> getListCoachNotValidateByClub(Long idClub);

}
