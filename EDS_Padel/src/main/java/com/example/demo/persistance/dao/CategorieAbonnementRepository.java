package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.persistance.entities.CategorieAbonnement;

import java.util.List;

public interface CategorieAbonnementRepository extends JpaRepository<CategorieAbonnement,Long> {
   List<CategorieAbonnement> getAllByClub(Club club);





}