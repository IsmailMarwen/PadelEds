package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.TypeAbonnementClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeAbonnementClubRepository extends JpaRepository<TypeAbonnementClub,Long> {
    List<TypeAbonnementClub> getAllByClub(Club club);

}
