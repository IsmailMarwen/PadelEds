package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TerrainRepository extends JpaRepository<Terrain,Long> {


}