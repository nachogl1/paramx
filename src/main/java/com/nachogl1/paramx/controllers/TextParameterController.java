package com.nachogl1.paramx.controllers;

import com.nachogl1.paramx.model.TextParameter;
import com.nachogl1.paramx.services.TextParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TextParameterController {

    @Autowired
    private TextParameterService service;

    @GetMapping("/textParameters/{paramUserId}")
    public ResponseEntity<?> getAllByUser(@PathVariable UUID paramUserId) {
        final List<TextParameter> responseBody = service.getAllByParamUser(paramUserId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PostMapping("/textParameters")
    public ResponseEntity<?> add(@RequestBody TextParameter parameter) {
        final TextParameter responseBody = service.save(parameter);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @PutMapping("/textParameters")
    public ResponseEntity<?> update(@RequestBody TextParameter parameter) {
        final TextParameter responseBody = service.save(parameter);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBody);
    }

    @DeleteMapping("/textParameters/{parameterId}")
    public ResponseEntity<?> delete(@PathVariable UUID parameterId) {
        service.delete(parameterId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


}
