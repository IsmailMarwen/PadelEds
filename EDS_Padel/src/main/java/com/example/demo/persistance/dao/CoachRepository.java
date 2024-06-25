package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach,Long> {
    Coach findByUsernameAndClub(String username, Club club);
    Coach findByEmailAndClub(String email, Club club);

    List<Coach> findByNom(String nom);

}
