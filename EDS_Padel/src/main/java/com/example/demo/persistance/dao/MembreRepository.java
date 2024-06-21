package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreRepository extends JpaRepository<Membre,Long> {
    Membre findByUsernameAndClub(String username, Club club);
    Membre findByEmailAndClub(String email, Club club);


}
