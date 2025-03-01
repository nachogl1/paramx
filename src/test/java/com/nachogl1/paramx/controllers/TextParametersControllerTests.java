package com.nachogl1.paramx.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nachogl1.paramx.model.ParamUser;
import com.nachogl1.paramx.model.TextParameter;
import com.nachogl1.paramx.services.TextParameterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TextParameterController.class)
public class TextParametersControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TextParameterService service;


    @Test
    void returnAllParameters() throws Exception {
        UUID userId = UUID.randomUUID();
        final ParamUser user = ParamUser.builder()
                .id(userId)
                .firstName("testName")
                .email("email")
                .secondName("secondName")
                .build();


        when(service.getAllByParamUser(user.getId())).thenReturn(List.of(
                TextParameter.builder()
                        .date(LocalDate.now())
                        .name("testName")
                        .valueParameter("testValue1")
                        .paramUser(user)
                        .build(),
                TextParameter.builder()
                        .date(LocalDate.now())
                        .name("testName2")
                        .paramUser(user)
                        .valueParameter("testValue2")
                        .build()

        ));

        MvcResult result = this.mockMvc.perform(get("/textParameters/{id}", userId))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); //to parse localdate object
        List<TextParameter> parameters = objectMapper.readValue(json, new TypeReference<>() {
        });

        assertNotNull(parameters);
        assertEquals(2, parameters.size());
    }
}
