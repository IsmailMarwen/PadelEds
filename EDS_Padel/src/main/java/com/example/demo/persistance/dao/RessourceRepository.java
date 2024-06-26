package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RessourceRepository extends JpaRepository<Ressource,Long> {
}
