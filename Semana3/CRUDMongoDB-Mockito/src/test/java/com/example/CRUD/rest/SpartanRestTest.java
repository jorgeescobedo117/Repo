package com.example.CRUD.rest;

import com.example.CRUD.model.Spartan;
import com.example.CRUD.Service.SpartanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SpartanRest.class)
class SpartanRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpartanService service;

    @Test
    void testGetAllSpartans() throws Exception {
        Spartan spartan = new Spartan("Spartan-117", "MasterChief", "Blue Team", 50);
        when(service.findAll()).thenReturn(List.of(spartan));

        mockMvc.perform(get("/api/spartans")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gamertag").value("MasterChief"));
    }
}
