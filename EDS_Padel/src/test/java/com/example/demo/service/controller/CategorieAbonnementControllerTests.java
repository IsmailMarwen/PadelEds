package com.example.demo.service.controller;

import com.example.demo.persistance.entities.CategorieAbonnement;
import com.example.demo.service.interfaces.ICategorieAbonnement;
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

@WebMvcTest(CategorieAbonnementController.class)
public class CategorieAbonnementControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategorieAbonnement iCategorieAbonnement;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCategorieAbonnements() throws Exception {
        CategorieAbonnement admin1 = new CategorieAbonnement();
        admin1.setDesignation("Des1");
        CategorieAbonnement admin2 = new CategorieAbonnement();
        admin2.setDesignation("Des2");

        List<CategorieAbonnement> admins = Arrays.asList(admin1, admin2);
        when(iCategorieAbonnement.getListCategorieAbonnement()).thenReturn(admins);

        mockMvc.perform(get("/api/CategorieAbonnement/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].designation").value("Des1"))
                .andExpect(jsonPath("$[1].designation").value("Des2"))
                .andDo(print());
    }

    @Test
    void testSaveCategorieAbonnement() throws Exception {
        CategorieAbonnement categorieAbonnement = new CategorieAbonnement();
        categorieAbonnement.setDesignation("Des1");

        when(iCategorieAbonnement.saveCategorieAbonnement(any(CategorieAbonnement.class))).thenReturn(categorieAbonnement);

        mockMvc.perform(post("/api/CategorieAbonnement/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categorieAbonnement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.designation").value("Des1"))
                .andDo(print());
    }

    @Test
    void testUpdateCategorieAbonnement() throws Exception {
        CategorieAbonnement categorieAbonnement = new CategorieAbonnement();
        categorieAbonnement.setDesignation("Des1");

        when(iCategorieAbonnement.updateCategorieAbonnement(any(CategorieAbonnement.class))).thenReturn(categorieAbonnement);

        mockMvc.perform(put("/api/CategorieAbonnement/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categorieAbonnement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.designation").value("Des1"))
                .andDo(print());
    }

    @Test
    void testGetCategorieAbonnementById() throws Exception {
        CategorieAbonnement categorieAbonnement = new CategorieAbonnement();
        categorieAbonnement.setDesignation("Des1");

        when(iCategorieAbonnement.getCategorieAbonnementByIdCategorieAbonnement(eq(1L))).thenReturn(categorieAbonnement);

        mockMvc.perform(get("/api/CategorieAbonnement/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.designation").value("Des1"))
                .andDo(print());
    }

    @Test
    void testDeleteCategorieAbonnement() throws Exception {
        when(iCategorieAbonnement.deleteCategorieAbonnement(eq(1L))).thenReturn(true);
        mockMvc.perform(delete("/api/CategorieAbonnement/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }
}
