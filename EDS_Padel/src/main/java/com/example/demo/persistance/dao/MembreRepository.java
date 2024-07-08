package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.lang.reflect.Member;
import java.util.List;

public interface MembreRepository extends JpaRepository<Membre,Long> {
    Membre findByUsernameAndClub(String username, Club club);
    Membre findByEmailAndClub(String email, Club club);
    Membre findByTelephoneAndClub(String tel,Club club);
    @Modifying
    @Transactional
    @Query("UPDATE Membre m SET m.password = :password, m.updated = true WHERE m.club.idClub = :idClub and m.idUtilisateur= :idUser")
    void updatePasswordMembreByIdClub(@Param("password") String password, @Param("idClub") Long idClub, @Param("idUser") Long idUser);
    List<Membre> getAllByClub(Club club);

}
