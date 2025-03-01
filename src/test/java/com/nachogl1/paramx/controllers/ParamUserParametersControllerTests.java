package com.nachogl1.paramx.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nachogl1.paramx.model.ParamUser;
import com.nachogl1.paramx.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ParamUserController.class)
public class ParamUserParametersControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService service;


    @Test
    void returnAnUser() throws Exception {

        final UUID id = UUID.randomUUID();
        final ParamUser expected = ParamUser.builder().id(id).firstName("testName").secondName("testSecondName").email("email").build();

        when(service.get(id)).thenReturn(expected);

        MvcResult result = this.mockMvc.perform(get("/users/{id}", id)).andExpect(status().isOk()).andReturn();

        String json = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); //to parse localdate object
        ParamUser user = objectMapper.readValue(json, new TypeReference<>() {
        });

        assertEquals(expected, user);

    }


}
