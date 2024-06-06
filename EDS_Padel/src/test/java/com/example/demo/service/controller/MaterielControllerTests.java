package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Materiel;
import com.example.demo.service.interfaces.IMateriel;
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

@WebMvcTest(MaterielController.class)
public class MaterielControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMateriel iMateriel;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllMateriels() throws Exception {
        Materiel admin1 = new Materiel();
        admin1.setReference("Mat1");
        Materiel admin2 = new Materiel();
        admin2.setReference("Mat2");

        List<Materiel> admins = Arrays.asList(admin1, admin2);
        when(iMateriel.getListMateriel()).thenReturn(admins);

        mockMvc.perform(get("/api/Materiel/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].reference").value("Mat1"))
                .andExpect(jsonPath("$[1].reference").value("Mat2"))
                .andDo(print());
    }

    @Test
    void testSaveMateriel() throws Exception {
        Materiel materiel = new Materiel();
        materiel.setReference("Mat1");

        when(iMateriel.saveMateriel(any(Materiel.class))).thenReturn(materiel);

        mockMvc.perform(post("/api/Materiel/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(materiel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reference").value("Mat1"))
                .andDo(print());
    }

    @Test
    void testUpdateMateriel() throws Exception {
        Materiel materiel = new Materiel();
        materiel.setReference("Mat1");

        when(iMateriel.updateMateriel(any(Materiel.class))).thenReturn(materiel);

        mockMvc.perform(put("/api/Materiel/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(materiel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reference").value("Mat1"))
                .andDo(print());
    }

    @Test
    void testGetMaterielById() throws Exception {
        Materiel materiel = new Materiel();
        materiel.setReference("Mat1");

        when(iMateriel.getMaterielByIdMateriel(eq(1L))).thenReturn(materiel);

        mockMvc.perform(get("/api/Materiel/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reference").value("Mat1"))
                .andDo(print());
    }

    @Test
    void testDeleteMateriel() throws Exception {
        when(iMateriel.deleteMateriel(eq(1L))).thenReturn(true);
        mockMvc.perform(delete("/api/Materiel/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }
}
