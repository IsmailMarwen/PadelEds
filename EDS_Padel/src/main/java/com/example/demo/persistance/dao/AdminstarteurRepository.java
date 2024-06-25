package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminstarteurRepository extends JpaRepository<Administrateur,Long> {
    Administrateur findByUsernameAndClub(String username, Club club);
    Administrateur findByEmailAndClub(String email, Club club);

}
