package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.util.List;

public interface AdminstarteurRepository extends JpaRepository<Administrateur,Long> {
    List<Administrateur> getAllByClub(Club club);
    Administrateur findByUsernameAndClub(String username, Club club);
    Administrateur findByEmailAndClub(String email, Club club);
    @Modifying
    @Transactional
    @Query("UPDATE Administrateur m SET m.password = :password, m.updated = true WHERE m.club.idClub = :idClub AND m.idUtilisateur= :idUser")
    void updatePasswordAdminByIdClub(@Param("password") String password, @Param("idClub") Long idClub,@Param("idUser") Long idUser);

}
