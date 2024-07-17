package com.example.demo.persistance.dao;


import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MaterielRepository extends JpaRepository<Materiel,Long> {

    List<Materiel> getAllByClub(Club club);

}