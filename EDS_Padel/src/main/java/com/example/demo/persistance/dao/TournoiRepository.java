package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TournoiRepository extends JpaRepository<Tournoi,Long> {


}