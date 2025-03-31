package com.nachogl1.paramx.controllers;

import com.nachogl1.paramx.model.ParameterName;
import com.nachogl1.paramx.model.TextParameter;
import com.nachogl1.paramx.services.TextParameterNameService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Log
public class TextParameterNameControllerV2 {

    @Autowired
    private TextParameterNameService service;

    @GetMapping("/textParameters/names/{paramUserId}")
    public ResponseEntity<List<ParameterName>> getAllTextParameterNamesByParamUserId(@PathVariable UUID paramUserId) {
        log.info(String.format("--*Fetching all params names for : %s *--", paramUserId.toString()));

        final List<ParameterName> responseBody = service.getAllTextParameterNamesByParamUserId(paramUserId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PostMapping("/textParameters/names")
    public ResponseEntity<ParameterName> add(@RequestBody ParameterName parameterName) {
        log.info(String.format("--*Creating instance of param name: %s *--", parameterName.getName()));
        final ParameterName responseBody = service.save(parameterName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseBody);
    }

    @PutMapping("/textParameters/names")
    public ResponseEntity<ParameterName> update(@RequestBody ParameterName parameterName) {
        log.info(String.format("--*Updating instance of param name: %s *--", parameterName.getId()));

        final ParameterName responseBody = service.save(parameterName);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @DeleteMapping("/textParameters/names/{parameterNameId}")
    public ResponseEntity<Void> delete(@PathVariable UUID parameterNameId) {
        log.info(String.format("--*Deleting instance of param name: %s *--", parameterNameId.toString()));

        service.delete(parameterNameId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


}
