package com.example.demo.service.impliments;


import com.example.demo.persistance.dao.CategorieAbonnementRepository;
import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.persistance.entities.Club;
import com.example.demo.service.interfaces.ICategorieAbonnement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class CategorieAbonnementService implements ICategorieAbonnement {
        @Autowired
        public CategorieAbonnementRepository categorieAbonnementRepository;
        @Autowired
        public ClubService clubService;

    @Override
    public CategorieAbonnement saveCategorieAbonnement(CategorieAbonnement categorieAbonnement) {
        return categorieAbonnementRepository.save(categorieAbonnement);
    }

    @Override
    public CategorieAbonnement updateCategorieAbonnement(CategorieAbonnement categorieAbonnement) {
        return categorieAbonnementRepository.saveAndFlush(categorieAbonnement);
    }




    @Override
    public boolean deleteCategorieAbonnement(Long id) {
        categorieAbonnementRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CategorieAbonnement> getListCategorieAbonnement() {
        return categorieAbonnementRepository.findAll();
    }

    @Override
    public CategorieAbonnement getCategorieAbonnementByIdCategorieAbonnement(Long id) {
        return categorieAbonnementRepository.findById(id).get();
    }

    @Override
    public List<CategorieAbonnement> getListCategorieAbonnementByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return categorieAbonnementRepository.getAllByClub(club);
    }
}


