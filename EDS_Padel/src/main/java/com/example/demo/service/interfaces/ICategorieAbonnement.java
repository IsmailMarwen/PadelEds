package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Banque;
import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.persistance.entities.Club;


import java.util.List;

public interface ICategorieAbonnement {
    CategorieAbonnement saveCategorieAbonnement(CategorieAbonnement categorieAbonnement);
    CategorieAbonnement updateCategorieAbonnement(CategorieAbonnement categorieAbonnement);


    boolean deleteCategorieAbonnement(Long id);
    List<CategorieAbonnement> getListCategorieAbonnement();
    CategorieAbonnement getCategorieAbonnementByIdCategorieAbonnement(Long id);
    List<CategorieAbonnement> getListCategorieAbonnementByClub(Long idClub);


}
