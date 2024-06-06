package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Membre;
import com.example.demo.persistance.entities.Terrain;
import com.example.demo.service.interfaces.IMembre;
import com.example.demo.service.interfaces.ITerrain;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TerrainController.class)


public class TerrainControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITerrain iTerrain;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllTerrains() throws Exception {
        Terrain terrain1 = new Terrain();
        terrain1.setNomTerrain("terrain");
        Terrain terrain2 = new Terrain();
        terrain2.setNomTerrain("terrain");

        List<Terrain> terrains = Arrays.asList(terrain1, terrain2);
        when(iTerrain.getListTerrain()).thenReturn(terrains);

        mockMvc.perform(get("/api/terrain/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nomTerrain").value("terrain"))
                .andExpect(jsonPath("$[1].nomTerrain").value("terrain"))
                .andDo(print());
    }

    @Test
    void testSaveTerrain() throws Exception {
        Terrain terrain = new Terrain();
        terrain.setNomTerrain("terrain");

        when(iTerrain.saveTerrain(any(Terrain.class))).thenReturn(terrain);

        mockMvc.perform(post("/api/terrain/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(terrain)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomTerrain").value("terrain"))
                .andDo(print());
    }

    @Test
    void testUpdateTerrain() throws Exception {
        Terrain terrain = new Terrain();
        terrain.setNomTerrain("terrain");

        when(iTerrain.updateTerrain(any(Terrain.class))).thenReturn(terrain);

        mockMvc.perform(put("/api/terrain/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(terrain)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomTerrain").value("terrain"))
                .andDo(print());
    }

    @Test
    void testGetTerrainById() throws Exception {
        Terrain terrain = new Terrain();
        terrain.setNomTerrain("terrain");

        when(iTerrain.getTerrainByIdTerrain(eq(1L))).thenReturn(terrain);

        mockMvc.perform(get("/api/terrain/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomTerrain").value("terrain"))
                .andDo(print());
    }

    @Test
    void testDeleteTerrain() throws Exception {
        when(iTerrain.deleteTerrain(eq(1L))).thenReturn(true);
        mockMvc.perform(delete("/api/terrain/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }
}
