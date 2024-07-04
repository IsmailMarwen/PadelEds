package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviseRepository extends JpaRepository<Devise,Long> {
    List<Devise> getAllByClub(Club club);

}
