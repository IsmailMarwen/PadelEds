package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.service.interfaces.ICoach;
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

@WebMvcTest(CoachController.class)


public class CoachControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICoach iCoach;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCoachs() throws Exception {
        Coach coach1 = new Coach();
        coach1.setNom("John Doe");
        Coach coach2 = new Coach();
        coach2.setNom("Jane Doe");

        List<Coach> coachs = Arrays.asList(coach1, coach2);
        when(iCoach.getListCoach()).thenReturn(coachs);

        mockMvc.perform(get("/api/coach/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nom").value("John Doe"))
                .andExpect(jsonPath("$[1].nom").value("Jane Doe"))
                .andDo(print());
    }

    @Test
    void testSaveCoach() throws Exception {
        Coach coach = new Coach();
        coach.setNom("John Doe");

        when(iCoach.saveCoach(any(Coach.class))).thenReturn(coach);

        mockMvc.perform(post("/api/coach/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coach)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"))
                .andDo(print());
    }

    @Test
    void testUpdateCoach() throws Exception {
        Coach coach = new Coach();
        coach.setNom("John Doe");

        when(iCoach.updateCoach(any(Coach.class))).thenReturn(coach);

        mockMvc.perform(put("/api/coach/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coach)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"))
                .andDo(print());
    }

    @Test
    void testGetCoachById() throws Exception {
        Coach coach = new Coach();
        coach.setNom("John Doe");

        when(iCoach.getCoachByIdCoach(eq(1L))).thenReturn(coach);

        mockMvc.perform(get("/api/coach/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("John Doe"))
                .andDo(print());
    }

    @Test
    void testDeleteCoach() throws Exception {
        when(iCoach.deleteCoach(eq(1L))).thenReturn(true);
        mockMvc.perform(delete("/api/coach/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }
}
