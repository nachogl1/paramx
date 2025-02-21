package com.nachogl1.paramx.controllers;

import com.nachogl1.paramx.model.NumericParameter;
import com.nachogl1.paramx.services.NumericParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class NumericParameterController {

    @Autowired
    private NumericParameterService service;

    @GetMapping("/numericParameters")
    public ResponseEntity<?> getAll() {
        final List<NumericParameter> responseBody = service.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PostMapping("/numericParameters")
    public ResponseEntity<?> add(@RequestBody NumericParameter parameter) {
        final NumericParameter responseBody = service.add(parameter);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PutMapping("/numericParameters")
    public ResponseEntity<?> update(@RequestBody NumericParameter parameter) {
        final NumericParameter responseBody = service.update(parameter);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @DeleteMapping("/numericParameters")
    public ResponseEntity<?> delete(@PathVariable UUID parameterId) {
        service.delete(parameterId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


}
