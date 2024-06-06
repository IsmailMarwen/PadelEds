package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.TerrainRepository;
import com.example.demo.persistance.dao.TerrainRepository;
import com.example.demo.persistance.entities.Terrain;
import com.example.demo.persistance.entities.Terrain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TerrainServiceTests {
    @Mock
    private TerrainRepository terrainRepository;

    @InjectMocks
    private TerrainService terrainService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTerrain() {
        Terrain Terrain = new Terrain();
        Terrain.setNomTerrain("terrain n1");

        when(terrainRepository.save(any(Terrain.class))).thenReturn(Terrain);

        Terrain savedTerrain = terrainService.saveTerrain(Terrain);

        assertThat(savedTerrain.getNomTerrain()).isEqualTo("terrain n1");
        verify(terrainRepository, times(1)).save(Terrain);
    }

    @Test
    void testGetListTerrains() {
        Terrain admin1 = new Terrain();
        admin1.setNomTerrain("Terrain1");
        Terrain admin2 = new Terrain();
        admin2.setNomTerrain("Terrain2");

        List<Terrain> admins = Arrays.asList(admin1, admin2);
        when(terrainRepository.findAll()).thenReturn(admins);

        List<Terrain> result = terrainService.getListTerrain();

        assertThat(result).hasSize(2);
        verify(terrainRepository, times(1)).findAll();
    }
    @Test
    void testGetTerrainByIdTerrain() {
        Terrain terrain = new Terrain();
        terrain.setIdTerrain(1);
        terrain.setNomTerrain("Terrain1");

        when(terrainRepository.findById(1L)).thenReturn(Optional.of(terrain));

        Terrain result = terrainService.getTerrainByIdTerrain(1L);

        assertThat(result).isNotNull();
        assertThat(result.getNomTerrain()).isEqualTo("Terrain1");
        verify(terrainRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateTerrain() {
        Terrain Terrain = new Terrain();
        Terrain.setIdTerrain(1);
        Terrain.setNomTerrain("Terrain1");

        when(terrainRepository.saveAndFlush(any(Terrain.class))).thenReturn(Terrain);

        Terrain updatedTerrain = terrainService.updateTerrain(Terrain);

        assertThat(updatedTerrain.getNomTerrain()).isEqualTo("Terrain1");
        verify(terrainRepository, times(1)).saveAndFlush(Terrain);
    }

    @Test
    void testDeleteTerrain() {
        Long id = 1L;

        doNothing().when(terrainRepository).deleteById(id);

        boolean isDeleted = terrainService.deleteTerrain(id);

        assertThat(isDeleted).isTrue();
        verify(terrainRepository, times(1)).deleteById(id);
    }
}
