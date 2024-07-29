package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteRepository extends JpaRepository<Activite,Long> {
     boolean existsActiviteByLibelle(String libelle);
     boolean existsActiviteByCouleur(String couleur);
     
}
