package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club,Long> {
    @Query("SELECT c FROM Club c WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(c.latitude)) * cos(radians(c.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(c.latitude)))) < :distance")
    List<Club> findClubsNearLocation(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distance);
    List<Club> findByVille(String ville);

    List<Club> findByNomClubContainingIgnoreCase(String nomClub);
}
