package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource,Long> {
    List<Ressource> getAllByClub(Club club);

}
