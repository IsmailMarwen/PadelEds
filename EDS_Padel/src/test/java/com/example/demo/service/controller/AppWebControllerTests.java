package com.example.demo.service.controller;
import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.service.interfaces.IAppWeb;
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

@WebMvcTest(AppWebController.class)

public class AppWebControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAppWeb iAppWeb;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllAppWebs() throws Exception {
        AppWeb appWeb1 = new AppWeb();
        appWeb1.setNomAppWeb("appWeb");
        AppWeb appWeb2 = new AppWeb();
        appWeb2.setNomAppWeb("appWeb");

        List<AppWeb> appWebs = Arrays.asList(appWeb1, appWeb2);
        when(iAppWeb.getListAppWeb()).thenReturn(appWebs);

        mockMvc.perform(get("/api/appWeb/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nomAppWeb").value("appWeb"))
                .andExpect(jsonPath("$[1].nomAppWeb").value("appWeb"))
                .andDo(print());
    }

    @Test
    void testSaveAppWeb() throws Exception {
        AppWeb appWeb = new AppWeb();
        appWeb.setNomAppWeb("appWeb");

        when(iAppWeb.saveAppWeb(any(AppWeb.class))).thenReturn(appWeb);

        mockMvc.perform(post("/api/appWeb/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appWeb)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomAppWeb").value("appWeb"))
                .andDo(print());
    }

    @Test
    void testUpdateAppWeb() throws Exception {
        AppWeb appWeb = new AppWeb();
        appWeb.setNomAppWeb("appWeb");

        when(iAppWeb.updateAppWeb(any(AppWeb.class))).thenReturn(appWeb);

        mockMvc.perform(put("/api/appWeb/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appWeb)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomAppWeb").value("appWeb"))
                .andDo(print());
    }

    @Test
    void testGetAppWebById() throws Exception {
        AppWeb appWeb = new AppWeb();
        appWeb.setNomAppWeb("appWeb");

        when(iAppWeb.getAppWebByIdAppWeb(eq(1L))).thenReturn(appWeb);

        mockMvc.perform(get("/api/appWeb/getById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomAppWeb").value("appWeb"))
                .andDo(print());
    }

    @Test
    void testDeleteAppWeb() throws Exception {
        when(iAppWeb.deleteAppWeb(eq(1L))).thenReturn(true);
        mockMvc.perform(delete("/api/appWeb/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());
    }
}
