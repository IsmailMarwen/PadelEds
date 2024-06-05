package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.service.impliments.CategorieAbonnementService;

import java.util.List;

public interface ICategorieAbonnement {
    CategorieAbonnement saveCategorieAbonnement(CategorieAbonnement categorieAbonnement);
    CategorieAbonnement updateCategorieAbonnement(CategorieAbonnement categorieAbonnement);


    boolean deleteCategorieAbonnement(Long id);
    List<CategorieAbonnement> getListCategorieAbonnement();
    CategorieAbonnement getCategorieAbonnementByIdCategorieAbonnement(Long id);

}
