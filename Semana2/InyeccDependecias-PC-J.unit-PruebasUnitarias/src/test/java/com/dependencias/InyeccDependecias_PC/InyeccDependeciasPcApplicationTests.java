package com.dependencias.InyeccDependecias_PC;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testVentaAsus() throws Exception {
        mockMvc.perform(get("/venta/Asus"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Intel i7")));
    }

    @Test
    void testVentaDell() throws Exception {
        mockMvc.perform(get("/venta/Dell"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("AMD Ryzen 5, 8GB RAM")));
    }

    @Test
    void testVentaMsi() throws Exception {
        mockMvc.perform(get("/venta/MSI"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Chip M1, 8GB RAM")));
    }
}
