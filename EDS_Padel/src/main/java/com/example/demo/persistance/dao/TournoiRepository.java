package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TournoiRepository extends JpaRepository<Tournoi,Long> {

    List<Tournoi> getAllByClub(Club club);

}