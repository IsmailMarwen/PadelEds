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
    List<Membre> getAllByClubAndValidation(Club club,boolean validation);
    @Query("SELECT m FROM Membre m WHERE m.club.idClub = :idClub AND m.validation = true AND m.idUtilisateur NOT IN (" +
            "SELECT mem.idUtilisateur FROM MatchDetail md JOIN md.membres mem JOIN md.reservation r " +
            "WHERE r.heureDebut = :heureDebut AND r.dateDernierRes = :dateDernierRes AND r.club.idClub = :idClub)")
    List<Membre> findNonParticipatingMembers(@Param("heureDebut") String heureDebut,
                                             @Param("dateDernierRes") String dateDernierRes,
                                             @Param("idClub") Long idClub);
    @Query("SELECT m FROM Membre m WHERE m.niveauPadel = :niveauPadel OR m.niveauPadel = :niveauPadel - 1 OR m.niveauPadel = :niveauPadel + 1")
    List<Membre> findMembersByNiveauPadelRange(@Param("niveauPadel") int niveauPadel);
}
