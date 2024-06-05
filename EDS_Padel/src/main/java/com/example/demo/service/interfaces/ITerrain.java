package com.example.demo.service.interfaces;


import com.example.demo.persistance.entities.Terrain;

import java.util.List;

public interface ITerrain {
    Terrain saveTerrain(Terrain terrain);
    Terrain updateTerrain(Terrain terrain);
    boolean deleteTerrain(Long id);
    List<Terrain> getListTerrain();
    Terrain getTerrainByIdTerrain(Long id);
}
