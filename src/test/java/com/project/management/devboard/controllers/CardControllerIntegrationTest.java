package com.project.management.devboard.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class CardControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getAll_returnsOk() throws Exception {
        MvcResult response = mockMvc.perform(get("/api/cards")).andReturn();
        System.out.println(response.getResponse().getContentAsString());
    }

}