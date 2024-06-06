package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Tournoi;
import com.example.demo.service.interfaces.ITournoi;
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

@WebMvcTest(TournoiController.class)
public class TournoiControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITournoi iTournoi;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllTournois() throws Exception {
        Tournoi admin1 = new Tournoi();
        admin1.setNomTournoi("Tournoi1");
        Tournoi admin2 = new Tournoi();
        admin2.setNomTournoi("Tournoi2");

        List<Tournoi> admins = Arrays.asList(admin1, admin2);
        when(iTournoi.getListTournoi()).thenReturn(admins);

        mockMvc.perform(get("/api/Tournoi/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nomTournoi").value("Tournoi1"))
                .andExpect(jsonPath("$[1].nomTournoi").value("Tournoi2"))
                .andDo(print());
    }

    @Test
    void testSaveTournoi() throws Exception {
        Tournoi tournoi = new Tournoi();
        tournoi.setNomTournoi("Tournoi1");

        when(iTournoi.saveTournoi(any(Tournoi.class))).thenReturn(tournoi);

        mockMvc.perform(post("/api/Tournoi/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tournoi)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomTournoi").value("Tournoi1"))
                .andDo(print());
    }

    @Test
    void testUpdateTournoi() throws Exception {
        Tournoi tournoi = new Tournoi();
        tournoi.setNomTournoi("Tournoi1");

        when(iTournoi.updateTournoi(any(Tournoi.class))).thenReturn(tournoi);

        mockMvc.perform(put("/api/Tournoi/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tournoi)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomTournoi").value("Tournoi1"))
                .andDo(print());
    }

    @Test
    void testGetTournoiById() throws Exception {
        Tournoi tournoi = new Tournoi();
        tournoi.setNomTournoi("Tournoi1");

        when(iTournoi.getTournoiByIdTournoi(eq(1L))).thenReturn(tournoi);

        mockMvc.perform(get("/api/Tournoi/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomTournoi").value("Tournoi1"))
                .andDo(print());
    }

    @Test
    void testDeleteTournoi() throws Exception {
        when(iTournoi.deleteTournoi(eq(1L))).thenReturn(true);
        mockMvc.perform(delete("/api/Tournoi/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }
}
