package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeAbonnementClubRepository extends JpaRepository<TypeAbonnementClub,Long> {
    List<TypeAbonnementClub> getAllByClub(Club club);
    List<TypeAbonnementClub> getAllByClubAndCoaches(Club club, Coach coach);
    List<TypeAbonnementClub> getAllByClubAndMembres(Club club, Membre membre);



}
