package com.nachogl1.paramx.controllers;

import com.nachogl1.paramx.services.TextParameterNameService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log
public class TextParameterNameControllerV1 {

    @Autowired
    private TextParameterNameService service;

    @GetMapping("/textParameters/names/{paramUserId}")
    public ResponseEntity<List<String>> getAllTextParameterNamesByParamUserId(@PathVariable UUID paramUserId) {
        log.info(String.format("--*Fetching all params names for : %s *--", paramUserId.toString()));

        final List<String> responseBody = service.getAllTextParameterNamesByParamUserId(paramUserId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }


}
