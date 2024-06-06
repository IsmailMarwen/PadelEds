package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MaterielRepository extends JpaRepository<Materiel,Long> {


}