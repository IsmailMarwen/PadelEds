package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Terrain;
import com.example.demo.service.interfaces.ITerrain;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "", allowedHeaders = "")
@RestController
@RequestMapping("/api/terrain")
public class TerrainController {
    private final ITerrain iTerrain;
    public TerrainController(ITerrain iTerrain){
        this.iTerrain=iTerrain;}
    @PostMapping("/add")
    Terrain save(@RequestBody Terrain Terrain) {
        Terrain a=iTerrain.saveTerrain(Terrain);
        return a ;
    }
    @PutMapping("/update")
    Terrain update(@RequestBody Terrain Terrain) {

        return iTerrain.updateTerrain(Terrain);
    }
    @GetMapping("/getAll")
    List<Terrain> getAllTerrains() {

        return iTerrain.getListTerrain();
    }

    @GetMapping("/getById/{id}")
    Terrain getAdminstarteurnById(@PathVariable Long id) {

        return iTerrain.getTerrainByIdTerrain(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iTerrain.deleteTerrain(id);
        return true;
    }

}
