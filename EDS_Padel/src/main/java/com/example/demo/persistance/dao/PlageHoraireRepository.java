package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.PlageHoraire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlageHoraireRepository extends JpaRepository<PlageHoraire,Long> {
    List<PlageHoraire> getAllByClub(Club club);

}
