package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.MaterielRepository;
import com.example.demo.persistance.dao.TerrainRepository;
import com.example.demo.persistance.entities.Terrain;
import com.example.demo.service.interfaces.IMateriel;
import com.example.demo.service.interfaces.ITerrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerrainService implements ITerrain {
    @Autowired
    public TerrainRepository terrainRepository;

    @Override
    public Terrain saveMembre(Terrain terrain) {
        return terrainRepository.save(terrain);
    }

    @Override
    public Terrain updateTerrain(Terrain terrain) {
        return terrainRepository.saveAndFlush(terrain);
    }

    @Override
    public boolean deleteTerrain(Long id) {
        terrainRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Terrain> getListTerrain() {
        return terrainRepository.findAll();
    }

    @Override
    public Terrain getTerrainByIdTerrain(Long id) {
        return terrainRepository.findById(id).get();
    }
}