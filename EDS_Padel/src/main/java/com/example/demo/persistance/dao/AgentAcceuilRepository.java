package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.AgentAcceuil;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AgentAcceuilRepository extends JpaRepository<AgentAcceuil,Long> {
    AgentAcceuil findByUsernameAndClub(String username, Club club);
    List<AgentAcceuil> getAllByClub(Club club);
    AgentAcceuil findByEmailAndClub(String email, Club club);
    AgentAcceuil findByTelephoneAndClub (String tel,Club club);
    @Modifying
    @Transactional
    @Query("UPDATE AgentAcceuil m SET m.password = :password, m.updated = true WHERE m.club.idClub = :idClub and m.idUtilisateur= :idUser")
    void updatePasswordAgentByIdClub(@Param("password") String password, @Param("idClub") Long idClub, @Param("idUser") Long idUser);

}
