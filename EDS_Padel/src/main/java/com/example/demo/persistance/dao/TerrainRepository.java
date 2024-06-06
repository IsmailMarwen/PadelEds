package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TerrainRepository extends JpaRepository<Terrain,Long> {


}