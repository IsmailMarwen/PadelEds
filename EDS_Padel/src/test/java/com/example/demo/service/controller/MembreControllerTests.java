package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Membre;
import com.example.demo.service.interfaces.IMembre;
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

@WebMvcTest(MembreController.class)

public class MembreControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMembre iMembre;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllMembres() throws Exception {
        Membre membre1 = new Membre();
        membre1.setNom("John Doe");
        Membre membre2 = new Membre();
        membre2.setNom("Jane Doe");

        List<Membre> membres = Arrays.asList(membre1, membre2);
        when(iMembre.getListMembre()).thenReturn(membres);

        mockMvc.perform(get("/api/membre/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nom").value("John Doe"))
                .andExpect(jsonPath("$[1].nom").value("Jane Doe"))
                .andDo(print());
    }

    @Test
    void testSaveMembre() throws Exception {
        Membre membre = new Membre();
        membre.setNom("John Doe");

        when(iMembre.saveMembre(any(Membre.class))).thenReturn(membre);

        mockMvc.perform(post("/api/membre/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(membre)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"))
                .andDo(print());
    }

    @Test
    void testUpdateMembre() throws Exception {
        Membre membre = new Membre();
        membre.setNom("John Doe");

        when(iMembre.updateMembre(any(Membre.class))).thenReturn(membre);

        mockMvc.perform(put("/api/membre/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(membre)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"))
                .andDo(print());
    }

    @Test
    void testGetMembreById() throws Exception {
        Membre membre = new Membre();
        membre.setNom("John Doe");

        when(iMembre.getMembreByIdMembre(eq(1L))).thenReturn(membre);

        mockMvc.perform(get("/api/membre/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"))
                .andDo(print());
    }

    @Test
    void testDeleteMembre() throws Exception {
        when(iMembre.deleteMembre(eq(1L))).thenReturn(true);
        mockMvc.perform(delete("/api/membre/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }
}
