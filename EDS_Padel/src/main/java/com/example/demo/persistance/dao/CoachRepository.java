package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach,Long> {
    Coach findByUsernameAndClub(String username, Club club);
    Coach findByEmailAndClub(String email, Club club);
    @Modifying
    @Transactional
    @Query("UPDATE Coach m SET m.password = :password, m.updated = true WHERE m.club.idClub = :idClub and m.idUtilisateur= :idUser")
    void updatePasswordCoachByIdClub(@Param("password") String password, @Param("idClub") Long idClub,@Param("idUser") Long idUser);

    List<Coach> findByNom(String nom);

}
