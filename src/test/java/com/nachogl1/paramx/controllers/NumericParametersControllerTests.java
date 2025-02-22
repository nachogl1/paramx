package com.nachogl1.paramx.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nachogl1.paramx.model.NumericParameter;
import com.nachogl1.paramx.services.NumericParameterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NumericParameterController.class)
public class NumericParametersControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NumericParameterService service;


    @Test
    void returnAllParameters() throws Exception {

        when(service.getAll()).thenReturn(List.of(
                NumericParameter.builder()
                        .date(LocalDate.now())
                        .name("testName")
                        .valueParameter(new BigDecimal("9.0"))
                        .build(),
                NumericParameter.builder()
                        .date(LocalDate.now())
                        .name("testName2")
                        .valueParameter(new BigDecimal("9.0"))
                        .build()

        ));

        MvcResult result = this.mockMvc.perform(get("/numericParameters"))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); //to parse localdate object
        List<NumericParameter> parameters = objectMapper.readValue(json, new TypeReference<>() {
        });

        assertNotNull(parameters);
        assertEquals(2, parameters.size());
    }


}
