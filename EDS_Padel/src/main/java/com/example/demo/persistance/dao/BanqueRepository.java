package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Banque;
import com.example.demo.persistance.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BanqueRepository extends JpaRepository<Banque,Long> {
    List<Banque> getAllByClub(Club club);
}
