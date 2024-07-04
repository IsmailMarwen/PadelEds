package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.TauxTva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TauxTvaRepository extends JpaRepository<TauxTva,Long> {
    List<TauxTva> getAllByClub(Club club);

}
