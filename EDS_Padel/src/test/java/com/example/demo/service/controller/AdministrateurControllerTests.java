package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.service.interfaces.IAdministrateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(AdministrateurController.class)
public class AdministrateurControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAdministrateur iAdministrateur;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllAdministrateurs() throws Exception {
        Administrateur admin1 = new Administrateur();
        admin1.setNom("John Doe");
        Administrateur admin2 = new Administrateur();
        admin2.setNom("Jane Doe");

        List<Administrateur> admins = Arrays.asList(admin1, admin2);
        when(iAdministrateur.getListAdminstarteur()).thenReturn(admins);

        mockMvc.perform(get("/api/administrateur/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nom").value("John Doe"))
                .andExpect(jsonPath("$[1].nom").value("Jane Doe"))
                .andDo(print());
    }

    @Test
    void testSaveAdministrateur() throws Exception {
        Administrateur administrateur = new Administrateur();
        administrateur.setNom("John Doe");

        when(iAdministrateur.saveAdminstrateur(any(Administrateur.class))).thenReturn(administrateur);

        mockMvc.perform(post("/api/administrateur/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(administrateur)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"))
                .andDo(print());
    }

    @Test
    void testUpdateAdministrateur() throws Exception {
        Administrateur administrateur = new Administrateur();
        administrateur.setNom("John Doe");

        when(iAdministrateur.updateAdminstarteur(any(Administrateur.class))).thenReturn(administrateur);

        mockMvc.perform(put("/api/administrateur/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(administrateur)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"))
                .andDo(print());
    }

    @Test
    void testGetAdministrateurById() throws Exception {
        Administrateur administrateur = new Administrateur();
        administrateur.setNom("John Doe");

        when(iAdministrateur.getAdminstarteurByIdAdminstarteur(eq(1L))).thenReturn(administrateur);

        mockMvc.perform(get("/api/administrateur/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"))
                .andDo(print());
    }

    @Test
    void testDeleteAdministrateur() throws Exception {
        when(iAdministrateur.deleteAdminstarteur(eq(1L))).thenReturn(true);
        mockMvc.perform(delete("/api/administrateur/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }
}
